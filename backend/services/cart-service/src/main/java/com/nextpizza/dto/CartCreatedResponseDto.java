package com.nextpizza.dto;

import java.io.Serializable;

public record CartCreatedResponseDto(

        String token
) implements Serializable {
}
