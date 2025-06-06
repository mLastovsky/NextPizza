package com.nextpizza.dto;

import java.time.Instant;
import java.util.List;

public record CartItemResponseDto(

        Long id,
        ProductItemResponseDto productItem,
        Long quantity,
        Instant createdAt,
        Instant updatedAt,
        List<IngredientResponseDto> ingredients
) {
}