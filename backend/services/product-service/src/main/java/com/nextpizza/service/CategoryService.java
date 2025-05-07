package com.nextpizza.service;

import com.nextpizza.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDto> getAllCategories();

}
