package com.ecomerce.product.products;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Debe ser un valor positivo")
        double availableQuantity,
        @Positive(message = "precio debe ser un valor positivo")
        BigDecimal price,
        @NotNull(message = "Product category is required")
        Integer categoryId
) {
}
