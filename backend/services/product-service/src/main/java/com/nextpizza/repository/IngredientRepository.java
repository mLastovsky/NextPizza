package com.nextpizza.repository;

import com.nextpizza.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByIdIn(List<Long> ids);

}
