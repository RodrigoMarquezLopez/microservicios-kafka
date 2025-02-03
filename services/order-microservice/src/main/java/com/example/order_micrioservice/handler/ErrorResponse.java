package com.example.order_micrioservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
