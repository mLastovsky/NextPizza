package com.nextpizza.service.impl;

import com.nextpizza.dto.UserResponseDto;
import com.nextpizza.exception.UserNotFoundException;
import com.nextpizza.mapper.UserMapper;
import com.nextpizza.dto.UserUpdateDto;
import com.nextpizza.model.User;
import com.nextpizza.repository.UserRepository;
import com.nextpizza.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto getMe(Jwt jwt) {
        var email = jwt.getClaimAsString("email");
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                format("User with email:: %s not found", email)
                        )
                );
    }

    @Override
    public UserResponseDto getById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                format("User with ID:: %s not found", id)
                        )
                );
    }

    @Override
    public UserResponseDto update(UserUpdateDto userUpdateDto, Jwt jwt) {
        var email = jwt.getClaimAsString("email");
        var user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                format("User with email:: %s not found", email)
                        )
                );

        updateUserFromDto(userUpdateDto, user);

        var updated = userRepository.save(user);
        return userMapper.toDto(updated);
    }

    private static void updateUserFromDto(UserUpdateDto userUpdateDto, User user) {
        if (userUpdateDto.fullName() != null) user.setFullName(userUpdateDto.fullName());
        if (userUpdateDto.email() != null) user.setEmail(userUpdateDto.email());
        if (userUpdateDto.password() != null) user.setPassword(userUpdateDto.password());
    }

    @Override
    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(
                    format("User with ID:: %s not found ", id)
            );
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto syncFromToken(Jwt jwt) {
        var email = jwt.getClaimAsString("email");
        var fullName = jwt.getClaimAsString("name");
        var provider = jwt.getIssuer().toString();
        var providerId = jwt.getSubject();

        var user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    var newUser = User.builder()
                            .email(email)
                            .fullName(fullName)
                            .provider(provider)
                            .providerId(providerId)
                            .build();

                    return userRepository.save(newUser);
                });

        return userMapper.toDto(user);
    }

}
