package com.nextpizza.controller;

import com.nextpizza.dto.ProductRequestDto;
import com.nextpizza.dto.ProductResponseDto;
import com.nextpizza.dto.ProductUpdateDto;
import com.nextpizza.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(productService.getProductsById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody @Valid ProductRequestDto productRequestDto
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(productRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductUpdateDto productRequestDto
    ) {
        return ResponseEntity
                .accepted()
                .body(productService.fullyUpdateProduct(id, productRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> patchProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductUpdateDto productRequestDto
    ) {
        return ResponseEntity
                .accepted()
                .body(productService.partitionUpdateProduct(id, productRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ){
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

}
