package com.nextpizza.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;


public record CartResponseDto(

        Long id,
        UserResponseDto user,
        BigDecimal totalAmount,
        String token,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        List<CartItemResponseDto> items
) implements Serializable {
}