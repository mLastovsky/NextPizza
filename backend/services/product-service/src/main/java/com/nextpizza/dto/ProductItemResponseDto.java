package com.nextpizza.dto;

import com.nextpizza.model.DoughType;
import com.nextpizza.model.Product;

import java.math.BigDecimal;

public record ProductItemResponseDto(

        Long id,
        Long size,
        BigDecimal price,
        String doughType,
        ProductResponseDto product
) {
}
