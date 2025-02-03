package com.example.notification_microservice.kafka.order;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
