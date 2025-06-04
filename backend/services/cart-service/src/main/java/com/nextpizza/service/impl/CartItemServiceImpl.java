package com.nextpizza.service.impl;

import com.nextpizza.dto.CartItemResponseDto;
import com.nextpizza.mapper.CartItemMapper;
import com.nextpizza.repository.CartItemRepository;
import com.nextpizza.service.CartItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public List<CartItemResponseDto> getAllCartItems() {
        return List.of();
    }

}
