package com.nextpizza.dto;

import java.time.Instant;
import java.util.List;

public record CategoryResponseDto(

        Long id,
        String name,
        List<ProductResponseDto> products,
        Instant createdAt,
        Instant updatedAt
) {
}
