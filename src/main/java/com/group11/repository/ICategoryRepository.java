package com.group11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.group11.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Short>, JpaSpecificationExecutor<Category>{
	public Category findByName(String name);

	public boolean existsByName(String name);

	public void deleteByIdIn(List<Short> ids);
}
