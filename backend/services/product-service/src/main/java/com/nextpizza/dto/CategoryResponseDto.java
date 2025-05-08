package com.nextpizza.dto;

import java.time.OffsetDateTime;
import java.util.List;

public record CategoryResponseDto(

        Long id,
        String name,
        List<ProductResponseDto> products,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
