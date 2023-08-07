package com.group11.controller;

import java.util.Arrays;
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

import com.group11.dto.order.OrderFormForCreating;
import com.group11.dto.order.OrderFormForUpdating;
import com.group11.entity.Order;
import com.group11.service.order.IOrderService;

@RestController
@RequestMapping(value = "api/v1/orders")
public class OrderController {
	@Autowired
	private IOrderService service;
	
	@GetMapping()
	public ResponseEntity<?> getAllOrders(
			Pageable pageable,
			@RequestParam(required = false)
			String search) {
		Page<Order> entities = service.getAllOrders(pageable, search);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> createOrder(@RequestBody OrderFormForCreating form) {
		service.createOrder(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}
	
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable short id,
                                              @RequestBody OrderFormForUpdating form) {
        service.updateOrder(id, form);
        return new ResponseEntity<>("Order updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable short id) {
        service.deleteOrders(Arrays.asList(id));
        return new ResponseEntity<>("Order deleted successfully!", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteOrders(@PathVariable(name = "ids") List<Short> ids) {
        service.deleteOrders(ids);
        return new ResponseEntity<>("Orders deleted successfully!", HttpStatus.OK);
    }

}
