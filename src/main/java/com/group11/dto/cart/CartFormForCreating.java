package com.group11.dto.cart;

import com.group11.entity.Cart;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartFormForCreating {
	short userId;
	
	public Cart toEntity() {
		return new Cart (userId);
	}
}
