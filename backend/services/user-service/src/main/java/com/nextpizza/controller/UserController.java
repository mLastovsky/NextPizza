package com.nextpizza.controller;

import com.nextpizza.dto.UserUpdateDto;
import com.nextpizza.dto.UserResponseDto;
import com.nextpizza.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMe(
            @AuthenticationPrincipal Jwt jwt
    ) {
        return ResponseEntity.ok(userService.getMe(jwt));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findByID(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PatchMapping("/update")
    public ResponseEntity<UserResponseDto> update(
            @Valid @RequestBody UserUpdateDto userUpdateDto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return ResponseEntity.ok(userService.update(userUpdateDto, jwt));
    }

    @PostMapping("/sync")
    public ResponseEntity<UserResponseDto> sync(
            @AuthenticationPrincipal Jwt jwt
    ){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.syncFromToken(jwt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {
        userService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

}
