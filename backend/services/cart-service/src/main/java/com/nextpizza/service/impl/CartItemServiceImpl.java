package com.nextpizza.service.impl;

import com.nextpizza.dto.CartItemUpdateDto;
import com.nextpizza.mapper.CartItemMapper;
import com.nextpizza.repository.CartItemRepository;
import com.nextpizza.service.CartItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public void deleteCartItem(Long id, String token) {

    }

    @Override
    public void updateCartItem(Long id, CartItemUpdateDto cartItemUpdateDto, String token) {

    }

}
