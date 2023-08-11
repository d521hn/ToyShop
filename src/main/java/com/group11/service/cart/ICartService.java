package com.group11.service.cart;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.group11.dto.cart.CartFormForCreating;
import com.group11.dto.cart.CartFormForUpdating;
import com.group11.entity.Cart;

public interface ICartService {
	Page<Cart> getAllCarts(Pageable pageable, String search);
	
	boolean isCartExistsById(short id);

	void createCart(CartFormForCreating form);

	Cart getCartByID(short id);

	void updateCart(short id, CartFormForUpdating form);

	void deleteCarts(List<Short> ids);
}
