package com.nextpizza.repository.specification;

import com.nextpizza.dto.ProductFilterDto;
import com.nextpizza.model.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

import static com.nextpizza.util.ValidationUtils.anyNonEmpty;

public class ProductSpecifications {

    public static Specification<ProductItem> withFilter(ProductFilterDto filter) {
        if (filter == null) {
            return Specification.where(null);
        }

        Specification<ProductItem> spec = Specification.where(null);

        if (anyNonEmpty(filter.priceFrom(), filter.priceTo())) {
            spec = spec.and(priceBetween(filter.priceFrom(), filter.priceTo()));
        }

        if (anyNonEmpty(filter.sizes())) {
            spec = spec.and(sizeIn(filter.sizes()));
        }

        if (anyNonEmpty(filter.pizzaTypes())) {
            spec = spec.and(doughTypeIn(filter.pizzaTypes()));
        }

        if (anyNonEmpty(filter.ingredients())) {
            spec = spec.and(hasIngredients(filter.ingredients()));
        }

        return spec;
    }

    public static Specification<ProductItem> priceBetween(BigDecimal priceFrom, BigDecimal priceTo) {
        return (root, query, criteriaBuilder) -> {
            if (!anyNonEmpty(priceFrom, priceTo)) {
                return criteriaBuilder.conjunction();
            }

            if (priceFrom == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceTo);
            }

            if (priceTo == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceFrom);
            }

            return criteriaBuilder.between(root.get("price"), priceFrom, priceTo);
        };
    }

    public static Specification<ProductItem> sizeIn(List<Integer> sizes) {
        return (root, query, criteriaBuilder) -> {
            if (!anyNonEmpty(sizes)) {
                return criteriaBuilder.conjunction();
            }
            return root.get("size").in(sizes);
        };
    }

    public static Specification<ProductItem> doughTypeIn(List<Integer> doughTypeIds) {
        return (root, query, criteriaBuilder) -> {
            if (!anyNonEmpty(doughTypeIds)) {
                return criteriaBuilder.conjunction();
            }
            Join<ProductItem, DoughType> doughTypeJoin = root.join("doughType");
            return doughTypeJoin.get("id").in(doughTypeIds);
        };
    }

    public static Specification<ProductItem> hasIngredients(List<Long> ingredientIds) {
        return (root, query, criteriaBuilder) -> {
            if (!anyNonEmpty(ingredientIds)) {
                return criteriaBuilder.conjunction();
            }

            query.distinct(true);

            Join<ProductItem, Product> productJoin = root.join("product", JoinType.INNER);
            Join<Product, ProductIngredient> productIngredientJoin = productJoin.join("ingredients", JoinType.INNER);
            Join<ProductIngredient, Ingredient> ingredientJoin = productIngredientJoin.join("ingredient", JoinType.INNER);

            return ingredientJoin.get("id").in(ingredientIds);
        };
    }

}
