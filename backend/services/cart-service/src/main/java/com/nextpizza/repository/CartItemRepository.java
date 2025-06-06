package com.nextpizza.repository;

import com.nextpizza.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("""
            SELECT ic.ingredientId FROM IngredientCartItem ic
            WHERE ic.cartItem.id = :cartItemId
            """)
    List<Long> findIngredientIdsByCartItemId(Long cartItemId);

}
