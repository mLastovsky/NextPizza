package com.nextpizza.dto;

import java.time.Instant;
import java.util.List;

public record ProductResponseDto(

        Long id,
        String name,
        String imageUrl,
        List<IngredientResponseDto> ingredients,
        List<ProductItemResponseDto> items,
        Instant createdAt,
        Instant updatedAt
) {
}
