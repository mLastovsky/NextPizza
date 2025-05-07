package com.nextpizza.repository.specification;

import com.nextpizza.dto.ProductFilterDto;
import com.nextpizza.model.Product;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecificationBuilder {

    public static Specification<Product> build(ProductFilterDto filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            var specifications = List.of(
                    new IngredientSpecification(filter),
                    new ProductItemSpecification(filter)
            );

            specifications.forEach(spec -> spec.applyFilter(predicates, root, query, cb));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
