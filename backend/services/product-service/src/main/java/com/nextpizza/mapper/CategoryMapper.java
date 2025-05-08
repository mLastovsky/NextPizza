package com.nextpizza.mapper;

import com.nextpizza.dto.CategoryResponseDto;
import com.nextpizza.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final ProductMapper productMapper;

    public CategoryResponseDto fromCategory(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getProducts().stream()
                        .map(productMapper::fromProduct)
                        .toList(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

}
