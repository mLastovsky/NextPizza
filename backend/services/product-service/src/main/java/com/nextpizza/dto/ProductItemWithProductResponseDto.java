package com.nextpizza.dto;

import java.math.BigDecimal;

public record ProductItemWithProductResponseDto(
        Long id,
        Long size,
        BigDecimal price,
        Long pizzaType,
        ProductWithoutItemsResponseDto product
) {
}
