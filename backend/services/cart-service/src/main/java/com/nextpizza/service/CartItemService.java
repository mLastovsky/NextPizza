package com.nextpizza.service;

import com.nextpizza.dto.CartItemUpdateDto;

public interface CartItemService {

    void deleteCartItem(Long id, String token);

    void updateCartItem(Long id, CartItemUpdateDto cartItemUpdateDto, String token);

}
