package com.nextpizza.service;


import com.nextpizza.dto.UserUpdateDto;
import com.nextpizza.dto.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public interface UserService {

    UserResponseDto getMe(Jwt jwt);

    UserResponseDto getById(UUID id);

    UserResponseDto update(@Valid UserUpdateDto userUpdateDto, Jwt jwt);

    void delete(UUID id);

    UserResponseDto syncFromToken(Jwt jwt);

}
