package com.nextpizza.service;

import com.nextpizza.dto.ProductItemResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductItemService {

    List<ProductItemResponseDto> getAllProductItems();

    ProductItemResponseDto getProductItemById(Long id);

    BigDecimal getProductPriceById(Long id);

}
