package com.nextpizza.service;

import com.nextpizza.dto.CartResponseDto;

import java.util.List;

public interface CartService {

    List<CartResponseDto> getAllCarts();

}
