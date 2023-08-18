package com.group11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.group11.entity.Cart;
import com.group11.entity.ProductCart;

public interface ICartRepository extends JpaRepository<Cart, Short>, JpaSpecificationExecutor<Cart>{
	public void deleteByIdIn(List<Short> ids);
	
	@Query(value = "SELECT * FROM CART p WHERE p.userId = :userId", nativeQuery = true)
	public Cart findByUserId(short userId);
}
