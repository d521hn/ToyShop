package com.group11.dto.productorder;

import com.group11.entity.ProductOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderFormForCreating {
	short productId;
	short orderId;
	int quantity;
	int price;
	
	public ProductOrder toEntity() {
		return new ProductOrder(productId, orderId, quantity, price);
	}
}
