package com.nextpizza.dto;

import java.time.Instant;
import java.util.List;

public record ErrorResponseDto(

        Instant timestamp,
        String error,
        String message,
        List<String> details
) {
}
