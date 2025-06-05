package com.nextpizza.controller;

import com.nextpizza.dto.CartItemUpdateDto;
import com.nextpizza.dto.CartResponseDto;
import com.nextpizza.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PatchMapping("/{id}")
    public ResponseEntity<CartResponseDto> updateItemQuantity(
            @PathVariable Long id,
            @RequestBody @Valid CartItemUpdateDto cartItemUpdateDto,
            @CookieValue(value = "cartToken", required = false) String token
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cartItemService.updateCartItem(id, cartItemUpdateDto, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartResponseDto> deleteItem(
            @PathVariable Long id,
            @CookieValue(value = "cartToken", required = false) String token
    ) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(cartItemService.deleteCartItem(id, token));
    }

}
