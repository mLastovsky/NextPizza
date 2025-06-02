package com.nextpizza.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationDto(

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 50, message = "Full name must be between 2 and 100 characters long")
    String fullname,

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 100 characters long")
    String password,

    @NotBlank(message = "Email is required")
    @Email(message = "Incorrect email format")
    String email
) {
}
