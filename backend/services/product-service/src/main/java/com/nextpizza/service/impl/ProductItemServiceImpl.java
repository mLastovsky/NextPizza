package com.nextpizza.service.impl;

import com.nextpizza.dto.ProductItemResponseDto;
import com.nextpizza.exception.ProductItemNotFoundException;
import com.nextpizza.mapper.ProductItemMapper;
import com.nextpizza.model.ProductItem;
import com.nextpizza.repository.ProductItemRepository;
import com.nextpizza.service.ProductItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductItemServiceImpl implements ProductItemService {

    private final ProductItemRepository productItemRepository;
    private final ProductItemMapper productItemMapper;

    @Override
    public List<ProductItemResponseDto> getAllProductItems() {
        return productItemRepository.findAll().stream()
                .map(productItemMapper::fromProductItem)
                .toList();
    }

    @Override
    public ProductItemResponseDto getProductItemById(Long id) {
        return productItemRepository.findById(id)
                .map(productItemMapper::fromProductItem)
                .orElseThrow(()-> new ProductItemNotFoundException(
                        format("ProductItem with ID:: %d not found", id)
                ));
    }

    @Override
    public BigDecimal getProductPriceById(Long id) {
        return productItemRepository.findById(id)
                .map(ProductItem::getPrice)
                .orElseThrow(()-> new ProductItemNotFoundException(
                        format("ProductItem with ID:: %d not found", id)
                ));
    }

}
