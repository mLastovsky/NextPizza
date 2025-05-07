package com.nextpizza.dto;

import java.math.BigDecimal;

public record ProductItemResponseDto(

        Long id,
        Long size,
        BigDecimal price,
        String doughType,
        ProductResponseDto product
) {
}
