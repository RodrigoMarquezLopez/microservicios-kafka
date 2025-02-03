package com.example.order_micrioservice.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest orderRequest){
        return Order.builder()
                .customerId(orderRequest.customerId())
                .id(orderRequest.id())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.amount())
                .paymentMethod(orderRequest.paymentMethod())
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse
                (
                        order.getId(),
                        order.getReference(),
                        order.getTotalAmount(),
                        order.getPaymentMethod(),
                        order.getCustomerId()
                );
    }
}
