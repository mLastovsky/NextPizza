package com.nextpizza.service.impl;

import com.nextpizza.dto.IngredientResponseDto;
import com.nextpizza.exception.IngredientNotFoundException;
import com.nextpizza.mapper.IngredientMapper;
import com.nextpizza.repository.IngredientRepository;
import com.nextpizza.service.IngredientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Transactional
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Override
    public List<IngredientResponseDto> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(ingredientMapper::fromIngredient)
                .toList();
    }

    @Override
    public IngredientResponseDto getIngredientById(Long id) {
        return ingredientRepository.findById(id)
                .map(ingredientMapper::fromIngredient)
                .orElseThrow(() -> new IngredientNotFoundException(
                        format("Ingredient with ID:: %d not found", id)
                ));
    }

}
