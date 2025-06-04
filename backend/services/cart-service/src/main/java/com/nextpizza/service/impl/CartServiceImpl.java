package com.nextpizza.service.impl;

import com.nextpizza.dto.CartResponseDto;
import com.nextpizza.mapper.CartMapper;
import com.nextpizza.repository.CartRepository;
import com.nextpizza.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public List<CartResponseDto> getAllCarts() {
        return List.of();
    }

}
