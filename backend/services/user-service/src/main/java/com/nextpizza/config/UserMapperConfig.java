package com.nextpizza.config;

import com.nextpizza.dto.UserRegistrationDTO;
import com.nextpizza.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapperConfig {
    UserMapperConfig INSTANCE = Mappers.getMapper(UserMapperConfig.class);

    UserRegistrationDTO userToUserRegistrationDTO(User user);
    User UserRegistrationDTOToUser(UserRegistrationDTO userRegistrationDTO);
}
