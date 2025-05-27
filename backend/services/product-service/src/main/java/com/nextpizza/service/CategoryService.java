package com.nextpizza.service;

import com.nextpizza.dto.CategoryResponseDto;
import com.nextpizza.dto.ProductFilterDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDto> getFilteredProducts(@Valid ProductFilterDto filter, Pageable pageable);

}
