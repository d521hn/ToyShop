package com.group11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.group11.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Short>, JpaSpecificationExecutor<Product>{
	public Product findByName(String name);

	public boolean existsByName(String name);

	public void deleteByIdIn(List<Short> ids);
}
