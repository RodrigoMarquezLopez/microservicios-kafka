package com.example.order_micrioservice.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import com.example.order_micrioservice.product.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Id required")
        @NotEmpty(message = "Id required")
        @NotBlank(message = "Id required")
        String customerId,
        @NotEmpty(message = "You should at least")
        List<PurchaseRequest> products
) {

}
