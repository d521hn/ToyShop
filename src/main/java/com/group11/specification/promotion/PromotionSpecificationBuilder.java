package com.group11.specification.promotion;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.group11.entity.Promotion;
import com.group11.specification.SearchCriteria;

public class PromotionSpecificationBuilder {
	public String search;

	public PromotionSpecificationBuilder(String search) {
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Promotion> build() {

		SearchCriteria seachCriteria = new SearchCriteria("value", "Like", search);

		Specification<Promotion> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new PromotionSpecification(seachCriteria);
		}

		return where;
	}
}
