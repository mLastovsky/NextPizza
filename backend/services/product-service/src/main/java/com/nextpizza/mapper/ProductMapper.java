package com.nextpizza.mapper;

import com.nextpizza.dto.ProductRequestDto;
import com.nextpizza.dto.ProductResponseDto;
import com.nextpizza.model.Category;
import com.nextpizza.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto fromProduct(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getImageUrl(),
                product.getCategory().getName(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public Product fromDto(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.name())
                .imageUrl(productRequestDto.imageUrl())
                .category(Category.builder()
                        .id(productRequestDto.categoryId())
                        .build())
                .build();
    }

}

