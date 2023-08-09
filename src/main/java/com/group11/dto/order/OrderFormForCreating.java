package com.group11.dto.order;

import com.group11.entity.Order;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderFormForCreating {
	String orderStatus;
	String deliveryAddress;
	short userId;
	
	public Order toEntity() {
		return new Order (orderStatus, deliveryAddress, userId);
	}
}
