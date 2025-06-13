package com.nextpizza.service;

import com.nextpizza.dto.CreateCartItemDto;
import com.nextpizza.dto.CartResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

public interface CartService {

    CartResponseDto getCartByToken(String token);

    CartResponseDto addItemToCart(@Valid CreateCartItemDto createCartItemDto, String token, HttpServletResponse response);

    CartResponseDto calculateCartTotalAmount(String token);

}
