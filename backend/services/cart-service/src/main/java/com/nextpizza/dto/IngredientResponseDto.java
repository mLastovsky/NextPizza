package com.nextpizza.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record IngredientResponseDto(

        Long id,
        String name,
        BigDecimal price,
        String imageUrl,
        Instant createdAt,
        Instant updatedAt
) {
}
