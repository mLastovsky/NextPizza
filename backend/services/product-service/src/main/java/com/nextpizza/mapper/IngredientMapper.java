package com.nextpizza.mapper;

import com.nextpizza.dto.IngredientResponseDto;
import com.nextpizza.model.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public IngredientResponseDto fromIngredient(Ingredient ingredient) {
        return new IngredientResponseDto(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getPrice(),
                ingredient.getImageUrl(),
                ingredient.getCreatedAt(),
                ingredient.getUpdatedAt()
        );
    }

}
