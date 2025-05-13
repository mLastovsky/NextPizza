package com.nextpizza.service;


import com.nextpizza.dto.UserAuthDTO;
import com.nextpizza.dto.UserRegistrationDTO;
import com.nextpizza.security.UserDetailsImpl;

public interface UserService {

    void register(UserRegistrationDTO userRegistrationDTO);

    void update(UserRegistrationDTO userRegistrationDTO);

    void deleteByEmail(String email);

    String getToken(UserAuthDTO userAuthDTO);

    UserDetailsImpl getUserDetails();
}
