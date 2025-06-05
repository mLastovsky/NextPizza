package com.nextpizza.dto;

import java.time.OffsetDateTime;
import java.util.List;

public record ErrorResponseDto(

        OffsetDateTime timestamp,
        String error,
        String message,
        List<String> details
) {
}
