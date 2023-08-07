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

import com.group11.dto.brand.BrandFormForCreating;
import com.group11.dto.brand.BrandFormForUpdating;
import com.group11.entity.Brand;
import com.group11.service.brand.IBrandService;

@RestController
@RequestMapping(value = "api/v1/brands")
public class BrandController {
	@Autowired
	private IBrandService service;

	@GetMapping()
	public ResponseEntity<?> getAllBrands(
			Pageable pageable,
			@RequestParam(required = false)
			String search) {
		Page<Brand> entities = service.getAllBrands(pageable, search);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<?> existsBrandById(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.isBrandExistsById(id), HttpStatus.OK);
	}


	@PostMapping()
	public ResponseEntity<?> createBrand(@RequestBody BrandFormForCreating form) {
		service.createBrand(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getBrandByID(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.getBrandByID(id), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateBrand(@PathVariable(name = "id") short id, @RequestBody BrandFormForUpdating form) {
		service.updateBrand(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deleteBrands(@PathVariable(name = "ids") List<Short> ids) {
		service.deleteBrands(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
}
