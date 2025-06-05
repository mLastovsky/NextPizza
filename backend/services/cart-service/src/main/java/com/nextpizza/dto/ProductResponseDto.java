package com.nextpizza.dto;

import java.time.OffsetDateTime;
import java.util.List;

public record ProductResponseDto(

        Long id,
        String name,
        String imageUrl,
        List<IngredientResponseDto> ingredients,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
