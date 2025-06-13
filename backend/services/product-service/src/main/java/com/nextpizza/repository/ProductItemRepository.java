package com.nextpizza.repository;

import com.nextpizza.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long>, JpaSpecificationExecutor<ProductItem> {
}
