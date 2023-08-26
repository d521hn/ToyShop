package com.group11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.group11.dto.productcart.ProductCartFormForCreating;
import com.group11.dto.productcart.ProductCartFormForUpdating;
import com.group11.entity.ProductCart;
import com.group11.repository.IProductCartRepository;
import com.group11.service.productcart.IProductCartService;
@RestController
@RequestMapping(value = "api/v1/productcarts")
public class ProductCartController {
	@Autowired
	private IProductCartService service;

	@GetMapping()
	public ResponseEntity<?> getAllProductCarts() {
		List<ProductCart> entities = service.getAllProductCarts();
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}
	
	@GetMapping(value = "/cartId/{cartId}")
	public ResponseEntity<?> getProductCartsByCartId(@PathVariable(name = "cartId") short cartId) {
		List<ProductCart> entities = service.getProductCartsByCartId(cartId);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> createProductCart(@RequestBody ProductCartFormForCreating form) {
		service.createProductCart(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updateProductCart(@RequestParam(name = "productId") short productId, @RequestParam(name = "cartId") short cardId, @RequestBody ProductCartFormForUpdating form) {
		service.updateProductCart(productId, cardId, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}
	
//	@DeleteMapping
//	public ResponseEntity<?> deleteProductCart(@RequestParam(name = "productId") short productId, @RequestParam(name = "cartId") short cardId) {
//		service.deleteProductCart(productId, cardId);
//		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
//	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteAllProductCart(@RequestParam(name = "cartId") short cardId) {
		service.deleteAllProductCart(cardId);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}

}
