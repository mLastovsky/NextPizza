package com.nextpizza.repository.specification;

import com.nextpizza.model.Product;
import jakarta.persistence.criteria.*;

import java.util.List;

public interface FilterSpecification {

    void applyFilter(List<Predicate> predicates, Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb);

}
