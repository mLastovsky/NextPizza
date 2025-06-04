package com.nextpizza.dto;

import com.nextpizza.model.Role;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserResponseDto (

        UUID id,
        String fullName,
        String email,
        Role role,
        String provider,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
