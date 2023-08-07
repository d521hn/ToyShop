package com.group11.specification.post;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.group11.entity.Post;
import com.group11.specification.SearchCriteria;

public class PostSpecificationBuilder {
	public String search;

	public PostSpecificationBuilder(String search) {
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Post> build() {

		SearchCriteria seachCriteria = new SearchCriteria("title", "Like", search);

		Specification<Post> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new PostSpecification(seachCriteria);
		}

		return where;
	}
}
