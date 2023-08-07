package com.group11.specification.category;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.group11.entity.Category;
import com.group11.specification.SearchCriteria;

public class CategorySpecificationBuilder {
	public String search;

	public CategorySpecificationBuilder(String search) {
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Category> build() {

		SearchCriteria seachCriteria = new SearchCriteria("name", "Like", search);

		Specification<Category> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new CategorySpecification(seachCriteria);
		}

		return where;
	}
}
