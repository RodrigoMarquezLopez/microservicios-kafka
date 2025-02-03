package com.ecomerce.product.products;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product required")
        Integer productId,
        @NotNull(message = "Product quantity required")
        double quantity
) {
}
