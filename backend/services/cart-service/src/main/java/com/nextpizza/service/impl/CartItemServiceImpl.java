package com.nextpizza.service.impl;

import com.nextpizza.dto.CartItemUpdateDto;
import com.nextpizza.dto.CartResponseDto;
import com.nextpizza.exception.CartItemNotFoundException;
import com.nextpizza.exception.CartTokenNotPresentException;
import com.nextpizza.repository.CartItemRepository;
import com.nextpizza.service.CartItemService;
import com.nextpizza.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartService cartService;

    @Override
    public CartResponseDto deleteCartItem(Long id, String token) {
        validateToken(token);

        var cartItem = cartItemRepository.findById(id)
                .orElseThrow(
                        () -> new CartItemNotFoundException(
                                format("Cart item with ID:: %d not found", id)
                        )
                );

        cartItemRepository.delete(cartItem);

        return cartService.calculateCartTotalAmount(token);
    }

    @Override
    public CartResponseDto updateCartItem(Long id, CartItemUpdateDto cartItemUpdateDto, String token) {
        validateToken(token);

        var cartItem = cartItemRepository.findById(id)
                .orElseThrow(
                        () -> new CartItemNotFoundException(
                                format("Cart item with ID:: %d not found", id)
                        )
                );

        cartItem.setQuantity(cartItemUpdateDto.quantity());

        return cartService.calculateCartTotalAmount(token);
    }

    private void validateToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new CartTokenNotPresentException("Cart token not present");
        }
    }

}
