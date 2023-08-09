package com.group11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.group11.entity.ProductOrder;

public interface IProductOrderRepository extends JpaRepository<ProductOrder, Short> {
	@Query(value = "SELECT * FROM PRODUCT_ORDER p WHERE p.productId = :productId AND p.orderId = :orderId", nativeQuery = true)
	public ProductOrder findById(short productId, short orderId);
	
	@Modifying
	@Query(value = "DELETE FROM PRODUCT_ORDER p WHERE p.productId = :productId AND p.orderId = :orderId", nativeQuery = true)
	public void deleteById(short productId, short orderId);
}
