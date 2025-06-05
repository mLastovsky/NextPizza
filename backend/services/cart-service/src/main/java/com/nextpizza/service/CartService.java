package com.nextpizza.service;

import com.nextpizza.dto.CartCreatedResponseDto;
import com.nextpizza.dto.CartRequestDto;
import com.nextpizza.dto.CartResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CartService {

    CartResponseDto getCartByToken(String token);

    CartCreatedResponseDto addItemToCart(@Valid CartRequestDto cartRequestDto, String token);

}
