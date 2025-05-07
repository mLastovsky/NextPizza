package com.nextpizza.mapper;

import com.nextpizza.dto.ProductItemResponseDto;
import com.nextpizza.model.ProductItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductItemMapper {

    private final ProductMapper productMapper;

    public ProductItemResponseDto fromProductItem(ProductItem productItem) {
        return new ProductItemResponseDto(
                productItem.getId(),
                productItem.getSize(),
                productItem.getPrice(),
                productItem.getDoughType() != null? productItem.getDoughType().getType() : null,
                productMapper.fromProduct(productItem.getProduct())
        );
    }

}
