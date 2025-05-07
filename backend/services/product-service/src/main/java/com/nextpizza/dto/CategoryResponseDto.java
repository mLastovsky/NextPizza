package com.nextpizza.dto;

import java.time.OffsetDateTime;

public record CategoryResponseDto(

        Long id,
        String name,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
