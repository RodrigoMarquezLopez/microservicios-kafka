package com.example.payment_microservice.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
    String id,
    @NotNull(message = "Nombre requerido")
    String firstname,
    @NotNull(message = "Apellido requerido")
    String lastname,
    @NotNull
    @Email(message = "Se requiere un correo valido")
    String email
) {
}
