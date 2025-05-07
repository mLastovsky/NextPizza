package com.nextpizza.repository.specification;

import com.nextpizza.dto.ProductFilterDto;
import com.nextpizza.model.Product;
import com.nextpizza.model.ProductIngredient;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.nextpizza.util.ValidationUtils.anyNonEmpty;

@RequiredArgsConstructor
public class IngredientSpecification implements FilterSpecification {

    private final ProductFilterDto filter;

    @Override
    public void applyFilter(List<Predicate> predicates, Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (anyNonEmpty(filter.ingredients())) {
            var productsWithAllIngredients = query.subquery(Long.class);
            var productIngredient = productsWithAllIngredients.from(ProductIngredient.class);
            var ingredient = productIngredient.join("ingredient");

            productsWithAllIngredients.select(productIngredient.get("product").get("id"))
                    .where(ingredient.get("id").in(filter.ingredients()))
                    .groupBy(productIngredient.get("product"))
                    .having(builder.equal(
                            builder.countDistinct(ingredient.get("id")),
                            filter.ingredients().size()));

            predicates.add(builder.in(root.get("id")).value(productsWithAllIngredients));
        }
    }

}
