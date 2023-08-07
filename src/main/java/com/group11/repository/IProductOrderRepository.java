package com.group11.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group11.entity.ProductOrder;

public interface IProductOrderRepository extends JpaRepository<ProductOrder, Short> {

}
