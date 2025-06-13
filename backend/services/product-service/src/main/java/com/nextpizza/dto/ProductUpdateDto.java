package com.nextpizza.dto;


import jakarta.validation.constraints.Size;

public record ProductUpdateDto(

        @Size(min = 2, message = "name length should be longer that 2 symbols")
        String name,

        String imageUrl,

        Long categoryId
) {
}
