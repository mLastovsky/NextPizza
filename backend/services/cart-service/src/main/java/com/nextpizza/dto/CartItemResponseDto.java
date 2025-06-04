package com.nextpizza.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

public record CartItemResponseDto(

        Long id,
        ProductItemResponseDto productItem,
        Long quantity,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) implements Serializable {
}