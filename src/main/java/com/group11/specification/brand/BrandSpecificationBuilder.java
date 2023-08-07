package com.group11.specification.brand;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.group11.entity.Brand;
import com.group11.specification.SearchCriteria;

public class BrandSpecificationBuilder {
	public String search;

	public BrandSpecificationBuilder(String search) {
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Brand> build() {

		SearchCriteria seachCriteria = new SearchCriteria("name", "Like", search);

		Specification<Brand> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new BrandSpecification(seachCriteria);
		}

		return where;
	}
}
