package com.example.order_micrioservice.customer;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
