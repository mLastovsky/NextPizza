package com.nextpizza.repository;

import com.nextpizza.model.IngredientCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientCartItemRepository extends JpaRepository<IngredientCartItem, Long> {
}
