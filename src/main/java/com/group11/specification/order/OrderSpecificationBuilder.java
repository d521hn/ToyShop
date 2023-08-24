package com.group11.specification.order;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.group11.dto.order.OrderFilter;
import com.group11.entity.Order;
import com.group11.specification.SearchCriteria;
import com.group11.specification.product.ProductSpecification;

public class OrderSpecificationBuilder {
	public String search;
	public OrderFilter filter;

	public OrderSpecificationBuilder(OrderFilter filter, String search) {
		this.filter = filter;
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Order> build() {

		SearchCriteria seachCriteria = new SearchCriteria("fullName", "searchByName", search);

		Specification<Order> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new OrderSpecification(seachCriteria);
		}

		if (filter.getMinDate() != null) {
			SearchCriteria minDate = new SearchCriteria("createdTime", "filterMinDate", filter.getMinDate());
			where = where == null ? new OrderSpecification(minDate) : where.and(new OrderSpecification(minDate));
		}

		if (filter.getMaxDate() != null) {
			SearchCriteria maxDate = new SearchCriteria("createdTime", "filterMaxDate", filter.getMaxDate());
			where = where == null ? new OrderSpecification(maxDate) : where.and(new OrderSpecification(maxDate));
		}

		return where;
	}
}
