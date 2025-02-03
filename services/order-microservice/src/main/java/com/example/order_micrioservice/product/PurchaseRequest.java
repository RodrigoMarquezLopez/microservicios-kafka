package com.example.order_micrioservice.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Products required")
        Integer productId,
        @Positive(message = "Cantidad positiva")
        double quantity
) {
}
