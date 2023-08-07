package com.group11.dto.productcart;

import com.group11.entity.ProductCart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCartFormForCreating {
	short productId;
	short cartId;
	int quantity;
	
	public ProductCart toEntity() {
		return new ProductCart(productId, cartId, quantity);
	}
}
