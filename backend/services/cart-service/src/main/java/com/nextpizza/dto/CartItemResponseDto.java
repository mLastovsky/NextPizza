package com.nextpizza.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public record CartItemResponseDto(

        Long id,
        ProductItemResponseDto productItem,
        // тут нужно возращать вариацию продукта который добавили
        // и инфу про это подукт
        Long quantity,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        List<IngredientResponseDto> ingredients
) implements Serializable {
}