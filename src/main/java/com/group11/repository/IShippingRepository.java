package com.group11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.group11.entity.Brand;
import com.group11.entity.Shipping;

public interface IShippingRepository extends JpaRepository<Shipping, Short>, JpaSpecificationExecutor<Shipping>{
	public Shipping findByEmail(String email);
	
	public Shipping findById(short id);

	public void deleteByIdIn(List<Short> ids);
}
