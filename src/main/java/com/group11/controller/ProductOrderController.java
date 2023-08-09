package com.group11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group11.dto.productorder.ProductOrderFormForCreating;
import com.group11.dto.productorder.ProductOrderFormForUpdating;
import com.group11.entity.ProductOrder;
import com.group11.service.productorder.IProductOrderService;
@RestController
@RequestMapping(value = "api/v1/productorders")
public class ProductOrderController {
	@Autowired
	private IProductOrderService service;

	@GetMapping()
	public ResponseEntity<?> getAllProductOrders() {
		List<ProductOrder> entities = service.getAllProductOrders();
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> createProductOrder(@RequestBody ProductOrderFormForCreating form) {
		service.createProductOrder(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updateProductOrder(@RequestParam(name = "productId") short productId, @RequestParam(name = "orderId") short cardId, @RequestBody ProductOrderFormForUpdating form) {
		service.updateProductOrder(productId, cardId, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteProductOrder(@RequestParam(name = "productId") short productId, @RequestParam(name = "orderId") short cardId) {
		service.deleteProductOrder(productId, cardId);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}

}
