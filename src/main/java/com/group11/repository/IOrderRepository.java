package com.group11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.group11.entity.Order;

public interface IOrderRepository extends JpaRepository<Order, Short>, JpaSpecificationExecutor<Order>{
	
}
