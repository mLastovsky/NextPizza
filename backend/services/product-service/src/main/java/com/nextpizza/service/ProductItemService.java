package com.nextpizza.service;

import com.nextpizza.dto.ProductItemResponseDto;

import java.util.List;

public interface ProductItemService {

    List<ProductItemResponseDto> getAllProductItems();

    ProductItemResponseDto getProductItemById(Long id);

}
