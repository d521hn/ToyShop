package com.group11.specification.cart;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.group11.entity.Cart;
import com.group11.specification.SearchCriteria;

public class CartSpecificationBuilder {
	public String search;

	public CartSpecificationBuilder(String search) {
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Cart> build() {

		SearchCriteria seachCriteria = new SearchCriteria("userId", "Like", search);

		Specification<Cart> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new CartSpecification(seachCriteria);
		}

		return where;
	}
}
