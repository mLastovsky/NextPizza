package com.nextpizza.controller;

import com.nextpizza.dto.CartItemResponseDto;
import com.nextpizza.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<List<CartItemResponseDto>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.getAllCartItems());
    }

}
