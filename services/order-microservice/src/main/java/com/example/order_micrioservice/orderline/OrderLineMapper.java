package com.example.order_micrioservice.orderline;

import com.example.order_micrioservice.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {


    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        System.out.println("Soy order line cantidad "+orderLineRequest.quantity());
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .productoId(orderLineRequest.productId())
                .quantity(orderLineRequest.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLineResponse orderLineResponse) {
        return new OrderLineResponse
                (
                        orderLineResponse.id(),
                        orderLineResponse.quantity()
                );
    }
}
