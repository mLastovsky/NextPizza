package com.nextpizza.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilter {

    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private List<Integer> pizzaTypes;
    private List<Integer> sizes;
    private List<Long> ingredientIds;

}
