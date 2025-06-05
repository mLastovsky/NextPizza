package com.nextpizza.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public record CartItemResponseDto(

        Long id,
        ProductItemResponseDto productItem,
        Long quantity,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        List<IngredientResponseDto> ingredients
) implements Serializable {
}