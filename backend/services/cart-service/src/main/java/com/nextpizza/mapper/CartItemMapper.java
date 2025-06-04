package com.nextpizza.mapper;

import com.nextpizza.dto.CartItemResponseDto;
import com.nextpizza.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemResponseDto toDto(CartItem cartItem);

}
