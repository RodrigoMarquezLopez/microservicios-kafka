package com.example.order_micrioservice.payment;

import com.example.order_micrioservice.customer.CustomerResponse;
import com.example.order_micrioservice.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
