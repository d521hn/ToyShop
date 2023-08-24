package com.group11.specification.order;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.group11.entity.Order;
import com.group11.specification.SearchCriteria;

public class OrderSpecification implements Specification<Order> {
	private static final long serialVersionUID = 1L;
	private SearchCriteria criteria;

	public OrderSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate predicate = null;
		if (criteria.getOperator().equalsIgnoreCase("Like")) {
			return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
		}
		if (criteria.getOperator().equalsIgnoreCase("searchByName")) {
			return criteriaBuilder.like(root.get("user").get(criteria.getKey()), "%" + criteria.getValue() + "%");
		}
        if (criteria.getOperator().equalsIgnoreCase("filterMinDate")) {
            Date minDate = (Date) criteria.getValue();
            predicate = criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), minDate);
        }

        if (criteria.getOperator().equalsIgnoreCase("filterMaxDate")) {
            Date maxDate = (Date) criteria.getValue();
            predicate = criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), maxDate);
        }
		return predicate;
	}
}
