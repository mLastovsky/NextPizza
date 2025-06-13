package com.nextpizza.dto;

import java.time.Instant;

public record UserResponseDto(

        String id,
        String fullName,
        String email,
        String role,
        String provider,
        Instant createdAt,
        Instant updatedAt
) {
}
