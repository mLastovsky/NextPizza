package com.nextpizza.service.impl;


import com.nextpizza.config.UserMapperConfig;
import com.nextpizza.dto.UserAuthDTO;
import com.nextpizza.dto.UserRegistrationDTO;
import com.nextpizza.exception.UserNotCreatedException;
import com.nextpizza.exception.UserNotFoundException;
import com.nextpizza.model.Role;
import com.nextpizza.model.User;
import com.nextpizza.repository.UserRepository;
import com.nextpizza.security.JwtCore;
import com.nextpizza.security.UserDetailsImpl;
import com.nextpizza.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;
    private final UserDetailsService userDetailsService;


    @Override
    @Transactional
    public void register(UserRegistrationDTO userRegistrationDTO) {
        User user = UserMapperConfig.INSTANCE.UserRegistrationDTOToUser(userRegistrationDTO);

        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new UserNotCreatedException("User with this email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setProvider(""); // Todo
        user.setProviderId(""); // Todo
        user.setCreatedAt(OffsetDateTime.now());
        user.setUpdatedAt(OffsetDateTime.now());

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(UserRegistrationDTO userRegistrationDTO) {
        User updatedUser = UserMapperConfig.INSTANCE.UserRegistrationDTOToUser(userRegistrationDTO);
        User userToUpdated = userRepository.findByEmail(updatedUser.getEmail()).orElseThrow(() -> new UserNotFoundException("User not found"));

        updatedUser.setId(userToUpdated.getId());
        updatedUser.setPassword(userToUpdated.getPassword());
        updatedUser.setRole(userToUpdated.getRole());
        updatedUser.setProvider(userToUpdated.getProvider());
        updatedUser.setProviderId(userToUpdated.getProviderId());
        updatedUser.setCreatedAt(userToUpdated.getCreatedAt());
        updatedUser.setUpdatedAt(OffsetDateTime.now());

        userRepository.save(updatedUser);
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteByEmail(user.getEmail());
    }

    @Override
    public String getToken(UserAuthDTO userAuthDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userAuthDTO.getEmail(), userAuthDTO.getPassword());
        authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userAuthDTO.getEmail());
        return jwtCore.generateToken(userDetails);
    }

    @Override
    public UserDetailsImpl getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }
}
