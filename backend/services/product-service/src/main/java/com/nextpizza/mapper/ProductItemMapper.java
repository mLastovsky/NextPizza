package com.nextpizza.mapper;

import com.nextpizza.dto.ProductItemResponseDto;
import com.nextpizza.dto.ProductItemWithProductResponseDto;
import com.nextpizza.dto.ProductResponseDto;
import com.nextpizza.dto.ProductWithoutItemsResponseDto;
import com.nextpizza.model.ProductIngredient;
import com.nextpizza.model.ProductItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductItemMapper {

    private final IngredientMapper ingredientMapper;

    public ProductItemResponseDto toDto(ProductItem productItem) {
        return new ProductItemResponseDto(
                productItem.getId(),
                productItem.getSize(),
                productItem.getPrice(),
                productItem.getDoughType() != null? productItem.getDoughType().getId() : null
        );
    }

    public ProductItemWithProductResponseDto toWithProductDto(ProductItem productItem) {
        return new ProductItemWithProductResponseDto(
                productItem.getId(),
                productItem.getSize(),
                productItem.getPrice(),
                productItem.getDoughType() != null? productItem.getDoughType().getId() : null,
                new ProductWithoutItemsResponseDto(
                        productItem.getProduct().getId(),
                        productItem.getProduct().getName(),
                        productItem.getProduct().getImageUrl(),
                        productItem.getProduct().getIngredients().stream()
                                .map(ProductIngredient::getIngredient)
                                .map(ingredientMapper::toDto)
                                .toList(),
                        productItem.getProduct().getCreatedAt(),
                        productItem.getProduct().getUpdatedAt()
                )
        );
    }

}
