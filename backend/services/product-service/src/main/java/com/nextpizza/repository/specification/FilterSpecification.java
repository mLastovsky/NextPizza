package com.nextpizza.repository.specification;

import jakarta.persistence.criteria.*;

import java.util.List;

public interface FilterSpecification<T> {

    void applyFilter(List<Predicate> predicates,
                     Root<T> root,
                     CriteriaQuery<?> query,
                     CriteriaBuilder cb
    );

}
