package com.nextpizza.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequestDto(

        @NotBlank(message = "name should not be blank")
        @NotNull(message = "name is mandatory ")
        @Size(min = 2, message = "name length should be longer that 2 symbols")
        String name,

        @NotBlank(message = "imageUrl should not be blank")
        @NotNull(message = "imageUrl is mandatory ")
        String imageUrl,

        @NotNull(message = "categoryID is mandatory")
        Long categoryId
) {
}
