package com.group11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group11.dto.category.CategoryFormForCreating;
import com.group11.dto.category.CategoryFormForUpdating;
import com.group11.entity.Category;
import com.group11.service.category.ICategoryService;

@RestController
@RequestMapping(value = "api/v1/categories")
public class CategoryController {
	@Autowired
	private ICategoryService service;

	@GetMapping()
	public ResponseEntity<?> getAllCategories(
			Pageable pageable,
			@RequestParam(required = false)
			String search) {
		Page<Category> entities = service.getAllCategories(pageable, search);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<?> existsCategoryById(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.isCategoryExistsById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<?> existsCategoryByName(@PathVariable(name = "name") String name) {
		return new ResponseEntity<>(service.isCategoryExistsByName(name), HttpStatus.OK);
	}


	@PostMapping()
	public ResponseEntity<?> createCategory(@RequestBody CategoryFormForCreating form) {
		service.createCategory(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCategoryByID(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.getCategoryByID(id), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable(name = "id") short id, @RequestBody CategoryFormForUpdating form) {
		service.updateCategory(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deleteCategories(@PathVariable(name = "ids") List<Short> ids) {
		service.deleteCategories(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
}
