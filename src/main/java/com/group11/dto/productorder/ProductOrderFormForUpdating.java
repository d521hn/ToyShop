package com.group11.dto.productorder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductOrderFormForUpdating {
	short productId;
	short orderId;
	int quantity;
	int price;
}
