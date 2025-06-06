package com.nextpizza.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


public record CartResponseDto(

        Long id,
        UserResponseDto user,
        BigDecimal totalAmount,
        String token,
        Instant createdAt,
        Instant updatedAt,
        List<CartItemResponseDto> items
) {
}