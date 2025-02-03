package com.ecomerce.customer.customer;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
/**
 * @Validated
 * Uso: Se utiliza para habilitar la validación de los datos de entrada en Beans.
 * Función: Activa la validación de restricciones declaradas con anotaciones como @NotNull, @Size, @Email, entre otras, en los atributos de la clase.
 * Requisito: Es parte de Spring Validation (y Hibernate Validator en el backend).
 * Efecto: Aplica validaciones cuando se utiliza la clase como entrada en controladores o servicios que esperan datos válidos.
 * */
public class Address {
    private String street;
    private String houseNumber;
    private String zipcode;
}
