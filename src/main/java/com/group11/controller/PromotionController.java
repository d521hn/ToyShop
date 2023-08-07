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

import com.group11.dto.promotion.PromotionFormForCreating;
import com.group11.dto.promotion.PromotionFormForUpdating;
import com.group11.entity.Promotion;
import com.group11.service.promotion.IPromotionService;

@RestController
@RequestMapping(value = "api/v1/promotions")
public class PromotionController {
	@Autowired
	private IPromotionService service;

	@GetMapping()
	public ResponseEntity<?> getAllPromotions(
			Pageable pageable,
			@RequestParam(required = false)
			String search) {
		Page<Promotion> entities = service.getAllPromotions(pageable, search);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<?> existsPromotionById(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.isPromotionExistsById(id), HttpStatus.OK);
	}


	@PostMapping()
	public ResponseEntity<?> createPromotion(@RequestBody PromotionFormForCreating form) {
		service.createPromotion(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getPromotionByID(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.getPromotionByID(id), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updatePromotion(@PathVariable(name = "id") short id, @RequestBody PromotionFormForUpdating form) {
		service.updatePromotion(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deletePromotions(@PathVariable(name = "ids") List<Short> ids) {
		service.deletePromotions(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
}
