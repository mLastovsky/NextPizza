package com.nextpizza.service;

import com.nextpizza.dto.CartItemUpdateDto;
import com.nextpizza.dto.CartResponseDto;

public interface CartItemService {

    CartResponseDto deleteCartItem(Long id, String token);

    CartResponseDto updateCartItem(Long id, CartItemUpdateDto cartItemUpdateDto, String token);

}
