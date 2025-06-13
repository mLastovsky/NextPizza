package com.nextpizza.controller;

import com.nextpizza.dto.CategoryResponseDto;
import com.nextpizza.dto.ProductFilterDto;
import com.nextpizza.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(
            @ModelAttribute @Valid ProductFilterDto filter,
            Pageable pageable
    ) {
        return ResponseEntity.ok(categoryService.getFilteredProducts(filter, pageable));
    }

}
