package com.example.notification_microservice.kafka;

import com.example.notification_microservice.email.EmailService;
import com.example.notification_microservice.kafka.order.Customer;
import com.example.notification_microservice.kafka.order.OrderConfirmation;
import com.example.notification_microservice.kafka.payment.PaymentConfirmation;
import com.example.notification_microservice.notification.Notification;
import com.example.notification_microservice.notification.NotificationRepository;
import com.example.notification_microservice.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccesNotification(PaymentConfirmation paymentConfirmation){
            log.info("Received Payment Confirmation: {}", paymentConfirmation);
            repository.save(
                    Notification.builder()
                            .type(NotificationType.PAYMENT_CONFIRMATION)
                            .notificationDate(LocalDateTime.now())
                            .paymentConfirmation(paymentConfirmation)
                            .build()
            );

            //Send email
        String customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
        try {
            emailService.sendPaymentSuccesEmail(
                    paymentConfirmation.customerEmail(),
                    customerName,
                    paymentConfirmation.amount(),
                    paymentConfirmation.orderReference()
            );
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "order-topic")
    public void consumePaymentSuccesNotification(OrderConfirmation orderConfirmation){
        log.info("Received Order Confirmation: {}", orderConfirmation);
        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        //Send email
        String customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();

        try {
            emailService.sendOrderConfirmationEmail(
                    orderConfirmation.customer().email(),
                    customerName,orderConfirmation.totalAmount(),
                    orderConfirmation.orderReference(),
                    orderConfirmation.products()
            );
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
