package com.example.payment_microservice.notification;

import com.example.payment_microservice.payment.Payment;
import com.example.payment_microservice.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
