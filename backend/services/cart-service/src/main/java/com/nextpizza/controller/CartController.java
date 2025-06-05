package com.nextpizza.controller;

import com.nextpizza.dto.CartCreatedResponseDto;
import com.nextpizza.dto.CartResponseDto;
import com.nextpizza.dto.CartRequestDto;
import com.nextpizza.service.CartService;
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
        //нужно отсортировать по created: desc
        // тут токен всегда есть его не может не быть
    }

    @PostMapping
    public ResponseEntity<CartCreatedResponseDto> addItemToCart(
            @RequestBody @Valid CartRequestDto cartRequestDto,
            @CookieValue(value = "cartToken", required = false) String token
    ) {
        return ResponseEntity.ok(cartService.addItemToCart(cartRequestDto, token));
    } // тут должны загенерить токен если его нет и тоже возращать cartResponseDto CartDTO
    //productItemId: number;
    //  ingredients?: number[];
}
