package com.nextpizza.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductFilterDto(

        BigDecimal priceFrom,
        BigDecimal priceTo,
        List<Integer> pizzaTypes,
        List<Integer> sizes,
        List<Long> ingredientIds
) {
}
