package com.group11.specification.product;

import org.springframework.data.jpa.domain.Specification;

import com.group11.entity.Product;
import com.group11.specification.SearchCriteria;

import org.springframework.util.StringUtils;

public class ProductSpecificationBuilder {
	public String search;

	public ProductSpecificationBuilder(String search) {
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Product> build() {

		SearchCriteria seachCriteria = new SearchCriteria("name", "Like", search);

		Specification<Product> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new ProductSpecification(seachCriteria);
		}

		return where;
	}
}
