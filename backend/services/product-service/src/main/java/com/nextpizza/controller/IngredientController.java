package com.nextpizza.controller;

import com.nextpizza.dto.IngredientResponseDto;
import com.nextpizza.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientResponseDto>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping
    public ResponseEntity<List<IngredientResponseDto>> getIngredientsByIds(
            @PathVariable List<Long> ids
    ) {
        return ResponseEntity.ok(ingredientService.getIngredientsByIds(ids));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponseDto> getIngredientById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<BigDecimal> getIngredientPriceById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(ingredientService.getIngredientPriceById(id));
    }

}
