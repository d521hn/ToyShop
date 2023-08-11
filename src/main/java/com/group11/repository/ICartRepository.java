package com.group11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.group11.entity.Cart;

public interface ICartRepository extends JpaRepository<Cart, Short>, JpaSpecificationExecutor<Cart>{
	public void deleteByIdIn(List<Short> ids);
}
