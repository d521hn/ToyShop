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

import com.group11.dto.cart.CartFormForCreating;
import com.group11.dto.cart.CartFormForUpdating;
import com.group11.entity.Cart;
import com.group11.service.cart.ICartService;

@RestController
@RequestMapping(value = "api/v1/carts")
public class CartController {
	@Autowired
	private ICartService service;

	@GetMapping()
	public ResponseEntity<?> getAllCarts(
			Pageable pageable,
			@RequestParam(required = false)
			String search) {
		Page<Cart> entities = service.getAllCarts(pageable, search);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<?> existsCartById(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.isCartExistsById(id), HttpStatus.OK);
	}


	@PostMapping()
	public ResponseEntity<?> createCart(@RequestBody CartFormForCreating form) {
		service.createCart(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCartByID(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.getCartByID(id), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateCart(@PathVariable(name = "id") short id, @RequestBody CartFormForUpdating form) {
		service.updateCart(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deleteCarts(@PathVariable(name = "ids") List<Short> ids) {
		service.deleteCarts(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
}

