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

import com.group11.dto.product.ProductFilter;
import com.group11.dto.product.ProductFormForCreating;
import com.group11.dto.product.ProductFormForUpdating;
import com.group11.entity.Product;
import com.group11.service.product.IProductService;

@RestController
@RequestMapping(value = "api/v1/products")
public class ProductController {
	@Autowired
	private IProductService service;

	@GetMapping()
	public ResponseEntity<?> getAllProducts(
			Pageable pageable,
			ProductFilter filter,
			@RequestParam(required = false)
			String search) {
		Page<Product> entities = service.getAllProducts(pageable, filter, search);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<?> existsProductById(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.isProductExistsById(id), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> createProduct(@RequestBody ProductFormForCreating form) {
		service.createProduct(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getProductByID(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.getProductByID(id), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable(name = "id") short id, @RequestBody ProductFormForUpdating form) {
		service.updateProduct(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deleteProducts(@PathVariable(name = "ids") List<Short> ids) {
		service.deleteProducts(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
}
