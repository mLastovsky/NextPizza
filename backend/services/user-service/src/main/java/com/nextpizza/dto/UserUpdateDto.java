package com.nextpizza.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(

    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters long")
    String fullName,

    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters long")
    String password,

    @Email(message = "Incorrect email format")
    String email
) {
}
