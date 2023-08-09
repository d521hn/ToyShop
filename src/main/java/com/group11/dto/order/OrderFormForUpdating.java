package com.group11.dto.order;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderFormForUpdating {
	String orderStatus;
	String deliveryAddress;
	short userId;
}
