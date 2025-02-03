package com.example.order_micrioservice.order;

import com.example.order_micrioservice.customer.CustomerClient;
import com.example.order_micrioservice.customer.CustomerResponse;
import com.example.order_micrioservice.exception.BussinesException;
import com.example.order_micrioservice.kafka.OrderConfirmation;
import com.example.order_micrioservice.kafka.OrderProducer;
import com.example.order_micrioservice.orderline.OrderLineRequest;
import com.example.order_micrioservice.orderline.OrderLineService;
import com.example.order_micrioservice.payment.PaymentClient;
import com.example.order_micrioservice.payment.PaymentRequest;
import com.example.order_micrioservice.product.ProductClient;
import com.example.order_micrioservice.product.PurchaseRequest;
import com.example.order_micrioservice.product.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        //Check the customer --> OpenFeign
        CustomerResponse customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BussinesException("No se puede crear la orden :: No existe cliente con ese id"));

        //purchase the product --> product-ms (RestTemplate)
        List<PurchaseResponse> products =  this.productClient.purchaseProducts(request.products());
        // persist order
        Order order =  this.repository.save(mapper.toOrder(request));
        //persist order lines
        for(PurchaseRequest purchaseRequest : request.products()){
            System.out.println(purchaseRequest.quantity());
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        //  start payment process
        PaymentRequest paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);
        // send the order confirmation --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(new OrderConfirmation
                (
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        products

                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException("No order found with id " + orderId));
    }
}
