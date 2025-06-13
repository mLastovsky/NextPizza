package com.nextpizza.dto;

import com.nextpizza.model.Role;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDto (

        UUID id,
        String fullName,
        String email,
        Role role,
        String provider,
        Instant createdAt,
        Instant updatedAt
) {
}
