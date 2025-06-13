package com.nextpizza.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateCartItemDto(

        @NotNull(message = "productItemId is mandatory")
        Long productItemId,

        List<Long> ingredients
) {
}
