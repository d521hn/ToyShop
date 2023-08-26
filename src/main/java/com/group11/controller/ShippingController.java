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

import com.group11.dto.shipping.ShippingFormForCreating;
//import com.group11.dto.shipping.ShippingFormForUpdating;
import com.group11.entity.Shipping;
import com.group11.service.shipping.IShippingService;

@RestController
@RequestMapping(value = "api/v1/shippings")
public class ShippingController {
	@Autowired
	private IShippingService service;

	@PostMapping()
	public ResponseEntity<?> createShipping(@RequestBody ShippingFormForCreating form) {
		service.createShipping(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable(name = "email") String email) {
		return new ResponseEntity<>(service.getByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
	}
}
