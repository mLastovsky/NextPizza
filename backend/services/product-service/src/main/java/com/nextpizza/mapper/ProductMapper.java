package com.nextpizza.mapper;

import com.nextpizza.dto.ProductRequestDto;
import com.nextpizza.dto.ProductResponseDto;
import com.nextpizza.dto.ProductWithoutItemsResponseDto;
import com.nextpizza.model.Category;
import com.nextpizza.model.Product;
import com.nextpizza.model.ProductIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final IngredientMapper ingredientMapper;
    private final ProductItemMapper productItemMapper;

    public ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getImageUrl(),
                product.getIngredients().stream()
                        .map(ProductIngredient::getIngredient)
                        .map(ingredientMapper::toDto)
                        .toList(),
                product.getProductItems().stream()
                        .map(productItemMapper::toDto)
                        .toList(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public Product toEntity(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.name())
                .imageUrl(productRequestDto.imageUrl())
                .category(Category.builder()
                        .id(productRequestDto.categoryId())
                        .build())
                .build();
    }

}

