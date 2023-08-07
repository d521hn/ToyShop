package com.group11.specification.order;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.group11.entity.Order;
import com.group11.specification.SearchCriteria;

public class OrderSpecificationBuilder {
	public String search;

	public OrderSpecificationBuilder(String search) {
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Order> build() {

		SearchCriteria seachCriteria = new SearchCriteria("orderStatus", "Like", search);

		Specification<Order> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new OrderSpecification(seachCriteria);
		}

		return where;
	}
}
