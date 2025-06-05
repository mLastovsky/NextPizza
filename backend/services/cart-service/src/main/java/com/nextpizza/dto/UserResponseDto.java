package com.nextpizza.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserResponseDto(

        String id,
        String fullName,
        String email,
        String role,
        String provider,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
