package com.group11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.group11.entity.Order;

public interface IOrderRepository extends JpaRepository<Order, Short>, JpaSpecificationExecutor<Order>{
	public void deleteByIdIn(List<Short> ids);
	@Query(value = 
	        "SELECT * FROM `Order` o "
	        + "WHERE o.shipId = :shipId "
	        + "ORDER BY id DESC "
	        , nativeQuery = true)
	public List<Order> getByShipId(short shipId);
	
}
