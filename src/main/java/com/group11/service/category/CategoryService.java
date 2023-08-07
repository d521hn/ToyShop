package com.group11.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group11.dto.category.CategoryFormForCreating;
import com.group11.dto.category.CategoryFormForUpdating;
import com.group11.entity.Category;
import com.group11.repository.ICategoryRepository;
import com.group11.specification.category.CategorySpecificationBuilder;

@Service
public class CategoryService implements ICategoryService {
	@Autowired 
	private ICategoryRepository repository;
	
	@Override
	public Page<Category> getAllCategories(Pageable pageable, String search) {
		CategorySpecificationBuilder specification = new CategorySpecificationBuilder(search);

		return repository.findAll(specification.build(), pageable);
	}

	@Override
	public boolean isCategoryExistsById(short id) {
		return repository.existsById(id);
	}

	@Override
	public void createCategory(CategoryFormForCreating form) {
		repository.save(form.toEntity());
	}

	@Override
	public Category getCategoryByID(short id) {
		return repository.findById(id).get();
	}

	@Override
	public void updateCategory(short id, CategoryFormForUpdating form) {
		Category entity = repository.findById(id).get();
		entity.setName(form.getName());
		repository.save(entity);
	}

	@Transactional
	public void deleteCategories(List<Short> ids) {
		repository.deleteByIdIn(ids);
	}
}
