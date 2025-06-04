package com.nextpizza.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProductItemResponseDto(

        Long id,
        Long size,
        BigDecimal price,
        Long pizzaType
) implements Serializable {
}
