package com.nextpizza.controller;

import com.nextpizza.dto.CartResponseDto;
import com.nextpizza.dto.CreateCartItemDto;
import com.nextpizza.service.CartService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponseDto> getCart(
            @CookieValue(value = "cartToken", required = false) String token
    ) {
        return ResponseEntity.ok(cartService.getCartByToken(token));
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> addItemToCart(
            @RequestBody @Valid CreateCartItemDto createCartItemDto,
            @CookieValue(value = "cartToken", required = false) String token,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(cartService.addItemToCart(createCartItemDto, token, response));
    }

}
