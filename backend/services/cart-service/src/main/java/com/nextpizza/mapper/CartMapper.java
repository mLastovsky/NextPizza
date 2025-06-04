package com.nextpizza.mapper;

import com.nextpizza.dto.CartResponseDto;
import com.nextpizza.model.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    Cart toEntity(CartResponseDto cartResponseDto);

    CartResponseDto toDto(Cart cart);

}
