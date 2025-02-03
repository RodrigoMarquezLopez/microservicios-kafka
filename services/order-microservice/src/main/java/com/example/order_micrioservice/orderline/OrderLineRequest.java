package com.example.order_micrioservice.orderline;

public record OrderLineRequest(Integer id,
                               Integer orderId,
                               Integer productId,
                               double quantity) {
}
