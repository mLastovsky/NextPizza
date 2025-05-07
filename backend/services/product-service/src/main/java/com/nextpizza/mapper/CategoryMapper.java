package com.nextpizza.mapper;

import com.nextpizza.dto.CategoryResponseDto;
import com.nextpizza.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponseDto fromCategory(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

}
