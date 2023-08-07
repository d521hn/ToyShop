package com.group11.dto.category;

import com.group11.entity.Category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryFormForCreating {
	String name;
	
	public Category toEntity() {
		return new Category(name);
	}
}
