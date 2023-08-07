package com.group11.dto.order;

import java.util.List;

import com.group11.entity.Order;
import com.group11.entity.ProductOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderFormForCreating {
	String orderStatus;
	String deliveryAddress;
	short userId;
	List<ProductOrder> items;
	
	public Order toEntity() {
		return new Order (orderStatus, deliveryAddress, userId, items);
	}
}
