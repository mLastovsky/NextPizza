package com.nextpizza.service.impl;

import com.nextpizza.dto.CategoryResponseDto;
import com.nextpizza.mapper.CategoryMapper;
import com.nextpizza.repository.CategoryRepository;
import com.nextpizza.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::fromCategory)
                .toList();
    }

}
