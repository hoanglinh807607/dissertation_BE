package com.dissertation.userservice.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CustomSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = 1L;

    private SearchCriteria searchCriteria;

    public CustomSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (this.searchCriteria == null) return null;
        if (this.searchCriteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(root.<String>get(this.searchCriteria.getKey()), this.searchCriteria.getValue().toString());
        } else if (this.searchCriteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(root.<String>get(this.searchCriteria.getKey()), this.searchCriteria.getValue().toString());
        } else if (this.searchCriteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(this.searchCriteria.getKey()).getJavaType() == String.class) {
                return builder.like(builder.lower(root.<String>get(this.searchCriteria.getKey())), "%" + this.searchCriteria.getValue().toString().toLowerCase() + "%");
            } else if (root.get(this.searchCriteria.getKey()).getJavaType() == Long.class) {
                return builder.like(builder.lower(builder.concat(root.<String>get(this.searchCriteria.getKey()), "::text")),
                        "%" + this.searchCriteria.getValue().toString().toLowerCase() + "%");
            } else {
                return builder.equal(root.get(this.searchCriteria.getKey()), this.searchCriteria.getValue());
            }
        }
        return null;
    }
}
