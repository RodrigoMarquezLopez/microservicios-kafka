package com.example.notification_microservice.email;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confimation.html","Payment succesfully processed"),
    ORDER_CONFIRMATION("order-confimation.html","Order succesfully processed");


    @Getter
    private final String template;

    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }

}
