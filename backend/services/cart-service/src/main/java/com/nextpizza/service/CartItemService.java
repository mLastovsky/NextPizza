package com.nextpizza.service;

import com.nextpizza.dto.CartItemResponseDto;

import java.util.List;

public interface CartItemService {

    List<CartItemResponseDto> getAllCartItems();

}
