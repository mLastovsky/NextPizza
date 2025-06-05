package com.nextpizza.controller;

import com.nextpizza.dto.CartItemUpdateDto;
import com.nextpizza.dto.MessageResponseDto;
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
    public ResponseEntity<Void> updateItemQuantity(
            @PathVariable Long id,
            @ModelAttribute @Valid CartItemUpdateDto cartItemUpdateDto,
            @CookieValue(value = "cartToken", required = false) String token
    ) {
        cartItemService.updateCartItem(id, cartItemUpdateDto, token);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDto> deleteItem(
            @PathVariable Long id,
            @CookieValue(value = "cartToken", required = false) String token
    ) {
        cartItemService.deleteCartItem(id, token);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
