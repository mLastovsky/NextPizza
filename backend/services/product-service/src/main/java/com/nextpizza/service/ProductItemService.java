package com.nextpizza.service;

import com.nextpizza.dto.ProductItemResponseDto;
import com.nextpizza.dto.ProductItemWithProductResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductItemService {

    List<ProductItemResponseDto> getAllProductItems();

    ProductItemWithProductResponseDto getProductItemById(Long id);

    BigDecimal getProductPriceById(Long id);

}
