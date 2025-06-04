package com.nextpizza.mapper;

import com.nextpizza.dto.UserResponseDto;
import com.nextpizza.dto.UserUpdateDto;
import com.nextpizza.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);

    User toUser(UserUpdateDto userUpdateDto);

}
