package com.nextpizza.repository.specification;

import com.nextpizza.dto.ProductFilterDto;
import com.nextpizza.model.Product;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.nextpizza.util.ValidationUtils.anyNonEmpty;

@RequiredArgsConstructor
public class ProductItemSpecification implements FilterSpecification {

    private final ProductFilterDto filter;

    @Override
    public void applyFilter(List<Predicate> predicates, Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (hasProductItemFilters()) {
            Join<Product, ?> itemsJoin = root.join("productItems");
            List<Predicate> itemPredicates = new ArrayList<>();

            addPriceFilter(itemPredicates, itemsJoin, cb);
            addSizeFilter(itemPredicates, itemsJoin);
            addPizzaTypeFilter(itemPredicates, itemsJoin);

            predicates.add(cb.and(itemPredicates.toArray(new Predicate[0])));
        }
    }

    private boolean hasProductItemFilters() {
        return anyNonEmpty(filter.priceFrom(), filter.priceTo(), filter.pizzaTypes(), filter.sizes());
    }

    private void addPriceFilter(List<Predicate> predicates, Join<Product, ?> itemsJoin, CriteriaBuilder cb) {
        if (anyNonEmpty(filter.priceFrom())) {
            predicates.add(cb.ge(itemsJoin.get("price"), filter.priceFrom()));
        }
        if (anyNonEmpty(filter.priceTo())) {
            predicates.add(cb.le(itemsJoin.get("price"), filter.priceTo()));
        }
    }

    private void addSizeFilter(List<Predicate> predicates, Join<Product, ?> itemsJoin) {
        if (anyNonEmpty(filter.sizes())) {
            predicates.add(itemsJoin.get("size").in(filter.sizes()));
        }
    }

    private void addPizzaTypeFilter(List<Predicate> predicates, Join<Product,?> itemsJoin) {
        if (anyNonEmpty(filter.pizzaTypes())) {
            predicates.add(itemsJoin.get("doughType").get("id").in(filter.pizzaTypes()));
        }
    }

}
