package com.nextpizza.service;

import com.nextpizza.dto.ProductRequestDto;
import com.nextpizza.dto.ProductResponseDto;
import com.nextpizza.dto.ProductUpdateDto;
import com.nextpizza.model.ProductFilter;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAllProducts(ProductFilter filter);

    ProductResponseDto getProductsById(Long id);

    ProductResponseDto createProduct(@Valid ProductRequestDto productRequestDto);

    ProductResponseDto fullyUpdateProduct(Long id, @Valid ProductUpdateDto productRequestDto);

    ProductResponseDto partiallyUpdateProduct(Long id, @Valid ProductUpdateDto productRequestDto);

    void deleteProductById(Long id);

    List<ProductResponseDto> searchProducts(String keyword, String limit);

}
