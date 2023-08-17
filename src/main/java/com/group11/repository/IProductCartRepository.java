package com.group11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group11.entity.ProductCart;

public interface IProductCartRepository extends JpaRepository<ProductCart, Short>, JpaSpecificationExecutor<ProductCart>{
	@Query(value = "SELECT * FROM PRODUCT_CART p WHERE p.userId = :userId", nativeQuery = true)
	public ProductCart findByUserId(short userId);
	
	@Query(value = "SELECT * FROM PRODUCT_CART p WHERE p.productId = :productId AND p.cartId = :cartId", nativeQuery = true)
	public ProductCart findById(short productId, short cartId);
	
	@Query(value = "SELECT * FROM PRODUCT_CART p WHERE p.cartId = :cartId", nativeQuery = true)
	public List<ProductCart> findByCartId(short cartId);
	
	@Modifying
	@Query(value = "DELETE FROM PRODUCT_CART p WHERE p.productId = :productId AND p.cartId = :cartId", nativeQuery = true)
	public void deleteById(short productId, short cartId);

}
