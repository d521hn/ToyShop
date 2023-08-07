package com.group11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.group11.entity.Promotion;

public interface IPromotionRepository extends JpaRepository<Promotion, Short>, JpaSpecificationExecutor<Promotion>{
	public Promotion findByCode(String code);

	public boolean existsByCode(String code);

	public void deleteByIdIn(List<Short> ids);
}
