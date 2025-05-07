package com.nextpizza.service;

import com.nextpizza.dto.IngredientResponseDto;

import java.util.List;

public interface IngredientService {

    List<IngredientResponseDto> getAllIngredients();

    IngredientResponseDto getIngredientById(Long id);

}
