package com.ecomerce.customer.customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Nombre del cliente requerido")
        String firstname,
        @NotNull(message = "Apellido del cliente requerido")
        String lastname,
        @Email(message = "Correo no valido")
        String email,
        Address address
) {


}
