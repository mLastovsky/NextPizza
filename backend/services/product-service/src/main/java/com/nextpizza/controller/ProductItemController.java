package com.nextpizza.controller;

import com.nextpizza.dto.ProductItemResponseDto;
import com.nextpizza.service.ProductItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-items")
@RequiredArgsConstructor
public class ProductItemController {

    private final ProductItemService productItemService;

    @GetMapping
    public ResponseEntity<List<ProductItemResponseDto>> getAllProductItems() {
        return ResponseEntity.ok(productItemService.getAllProductItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductItemResponseDto> getProductItemById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(productItemService.getProductItemById(id));
    }

}
