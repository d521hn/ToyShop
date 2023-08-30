package com.group11.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.group11.entity.Product;
import com.group11.specification.SearchCriteria;

public class ProductSpecification  implements Specification<Product> {
	private static final long serialVersionUID = 1L;
	private SearchCriteria criteria;

	public ProductSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (criteria.getOperator().equalsIgnoreCase("searchByName")) {
			return criteriaBuilder.or(
					criteriaBuilder.like(root.get(criteria.getKey()), criteria.getValue() + " %"),
					criteriaBuilder.like(root.get(criteria.getKey()), "% " + criteria.getValue() + " %"),
					criteriaBuilder.like(root.get(criteria.getKey()), "% " + criteria.getValue())
					);
		}
		if (criteria.getOperator().equalsIgnoreCase("filterByAgeGroup")) {
			return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
		}
		if (criteria.getOperator().equalsIgnoreCase("filterByBrand")) {
			return criteriaBuilder.like(root.get("brand").get(criteria.getKey()), "%" + criteria.getValue() + "%");
		}
		if (criteria.getOperator().equalsIgnoreCase("filterByCate")) {
			return criteriaBuilder.like(root.get("cate").get(criteria.getKey()), "%" + criteria.getValue() + "%");
		}
		if (criteria.getOperator().equalsIgnoreCase("filterMinPrice")) {
			return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
		}

		if (criteria.getOperator().equalsIgnoreCase("filterMaxPrice")) {
			return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
		}
		return null;
	}
}
