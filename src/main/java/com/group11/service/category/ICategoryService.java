package com.group11.service.category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.group11.dto.category.CategoryFormForCreating;
import com.group11.dto.category.CategoryFormForUpdating;
import com.group11.entity.Category;

public interface ICategoryService {
	Page<Category> getAllCategories(Pageable pageable, String search);
	
	boolean isCategoryExistsById(short id);

	void createCategory(CategoryFormForCreating form);

	Category getCategoryByID(short id);

	void updateCategory(short id, CategoryFormForUpdating form);

	void deleteCategories(List<Short> ids);
}
