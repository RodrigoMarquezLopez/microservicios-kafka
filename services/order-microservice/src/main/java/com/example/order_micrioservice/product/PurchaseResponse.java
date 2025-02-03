package com.example.order_micrioservice.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer productId,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price
) {
}
