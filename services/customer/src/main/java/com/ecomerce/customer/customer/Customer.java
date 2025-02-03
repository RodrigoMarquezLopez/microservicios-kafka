package com.ecomerce.customer.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document

/**
 * @Document
 * Uso: Se utiliza en proyectos que trabajan con bases de datos MongoDB.
 * Función: Marca una clase como un documento que será almacenado en una colección de MongoDB.
 * Requisito: Forma parte de Spring Data MongoDB.
 * Efecto: Indica que la clase representará un documento dentro de MongoDB, y permite que Spring gestione automáticamente su persistencia.
 * */

public class Customer {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
