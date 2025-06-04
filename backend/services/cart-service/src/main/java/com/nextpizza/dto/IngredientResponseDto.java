package com.nextpizza.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record IngredientResponseDto(

        Long id,
        String name,
        BigDecimal price,
        String imageUrl,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) implements Serializable {
}
