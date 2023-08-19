package com.group11.specification.user;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.group11.entity.User;
import com.group11.specification.SearchCriteria;

public class UserSpecificationBuilder {
	public String search;

	public UserSpecificationBuilder(String search) {
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<User> build() {

		SearchCriteria seachCriteria = new SearchCriteria("name", "Like", search);

		Specification<User> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new UserSpecification(seachCriteria);
		}

		return where;
	}
}
