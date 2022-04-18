package com.zerokikr.spring.firstapp.repositories.specifications;

import com.zerokikr.spring.firstapp.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecs {
    public static Specification<Product> titleContains (String word) {
        return (Specification<Product>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Product> priceGreaterThanOrEqual (Integer value) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
            }
        };
    }

    public static Specification<Product> priceLesserThanOrEqual (Integer value) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
            }
        };
    }

}
