package com.group11.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group11.dto.cart.CartFormForCreating;
import com.group11.dto.cart.CartFormForUpdating;
import com.group11.entity.Cart;
import com.group11.repository.ICartRepository;
import com.group11.specification.cart.CartSpecificationBuilder;

@Service
public class CartService implements ICartService {
	@Autowired 
	private ICartRepository repository;
	
	@Override
	public Page<Cart> getAllCarts(Pageable pageable, String search) {
		CartSpecificationBuilder specification = new CartSpecificationBuilder(search);

		return repository.findAll(specification.build(), pageable);
	}

	@Override
	public boolean isCartExistsById(short id) {
		return repository.existsById(id);
	}

	@Override
	public void createCart(CartFormForCreating form) {
		repository.save(form.toEntity());
	}

	@Override
	public Cart getCartByID(short id) {
		return repository.findById(id).get();
	}

	@Override
	public void updateCart(short id, CartFormForUpdating form) {
		Cart entity = repository.findById(id).get();
		entity.setUserId(form.getUserId());
		repository.save(entity);
	}

	@Transactional
	public void deleteCarts(List<Short> ids) {
		repository.deleteByIdIn(ids);
	}
}
