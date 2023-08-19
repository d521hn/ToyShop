package com.group11.dto.order;

import java.util.Date;

import com.group11.entity.Order;
import com.group11.entity.Order.PaymentMethod;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderFormForCreating {
	short userId;
	short shipId;
	String orderStatus;
	Date createdTime;
	PaymentMethod paymentMethod;
	String paymentStatus;
	
	
	public Order toEntity() {
		return new Order (userId, shipId, orderStatus, createdTime, paymentMethod, paymentStatus);
	}
}
