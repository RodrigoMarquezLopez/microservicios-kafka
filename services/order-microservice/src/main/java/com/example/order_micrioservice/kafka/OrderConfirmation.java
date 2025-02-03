package com.example.order_micrioservice.kafka;

import com.example.order_micrioservice.customer.CustomerResponse;
import com.example.order_micrioservice.order.PaymentMethod;
import com.example.order_micrioservice.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReferences,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {

}
