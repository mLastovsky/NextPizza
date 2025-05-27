package com.nextpizza.service.impl;

import com.nextpizza.dto.ProductRequestDto;
import com.nextpizza.dto.ProductResponseDto;
import com.nextpizza.dto.ProductUpdateDto;
import com.nextpizza.exception.CategoryNotFoundException;
import com.nextpizza.exception.ProductNotFoundException;
import com.nextpizza.mapper.ProductMapper;
import com.nextpizza.model.Category;
import com.nextpizza.model.Product;
import com.nextpizza.dto.ProductFilterDto;
import com.nextpizza.repository.CategoryRepository;
import com.nextpizza.repository.ProductRepository;
import com.nextpizza.service.ProductService;
import com.nextpizza.repository.specification.ProductSpecifications;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponseDto> getFilteredProducts(@Valid ProductFilterDto filter, Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::fromProduct)
                .getContent();
    }

    @Override
    public ProductResponseDto getProductsById(Long id) {
        return productMapper.fromProduct(findProductByIdOrThrow(id));
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        var savedEntity = productRepository.save(productMapper.fromDto(productRequestDto));
        return productMapper.fromProduct(savedEntity);
    }

    @Override
    public ProductResponseDto fullyUpdateProduct(Long id, ProductUpdateDto productRequestDto) {
        var existingProduct = findProductByIdOrThrow(id);
        updateAllProductFields(existingProduct, productRequestDto);
        return productMapper.fromProduct(productRepository.save(existingProduct));
    }

    @Override
    public ProductResponseDto partiallyUpdateProduct(Long id, ProductUpdateDto productRequestDto) {
        var existingProduct = findProductByIdOrThrow(id);
        updateNonNullProductFields(existingProduct, productRequestDto);
        return productMapper.fromProduct(productRepository.save(existingProduct));
    }

    @Override
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(format("Product with ID:: %d not found", id));
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> searchProducts(String keyword, String limit) {
        return productRepository.findByNameContainingIgnoreCase(keyword, PageRequest.of(0, Integer.parseInt(limit)))
                .stream()
                .map(productMapper::fromProduct)
                .toList();
    }

    private Product findProductByIdOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        format("Product with ID:: %d not found", id)
                ));
    }

    private Category findCategoryByIdOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        format("Category with ID:: %d not found", id)
                ));
    }

    private void updateAllProductFields(Product product, ProductUpdateDto dto) {
        product.setName(dto.name());
        product.setImageUrl(dto.imageUrl());
        updateCategoryIfPresent(product, dto.categoryId());
    }

    private void updateNonNullProductFields(Product product, ProductUpdateDto dto) {
        if (dto.name() != null) {
            product.setName(dto.name());
        }
        if (dto.imageUrl() != null) {
            product.setImageUrl(dto.imageUrl());
        }
        updateCategoryIfPresent(product, dto.categoryId());
    }

    private void updateCategoryIfPresent(Product product, Long categoryId) {
        if (categoryId != null) {
            product.setCategory(findCategoryByIdOrThrow(categoryId));
        }
    }

}
