package com.group11.dto.order;

import java.util.Date;

import com.group11.entity.Order.PaymentMethod;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderFormForUpdating {
	short userId;
	short shipId;
	String orderStatus;
	Date createdTime;
	PaymentMethod paymentMethod;
}
