package com.nextpizza.dto;

import java.time.OffsetDateTime;

public record ProductResponseDto(

        Long id,
        String name,
        String imageUrl,
        String category,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
