package com.group11.service.promotion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group11.dto.promotion.PromotionFormForCreating;
import com.group11.dto.promotion.PromotionFormForUpdating;
import com.group11.entity.Promotion;
import com.group11.repository.IPromotionRepository;
import com.group11.specification.promotion.PromotionSpecificationBuilder;

@Service
public class PromotionService implements IPromotionService {
	@Autowired 
	private IPromotionRepository repository;
	
	@Override
	public Page<Promotion> getAllPromotions(Pageable pageable, String search) {
		PromotionSpecificationBuilder specification = new PromotionSpecificationBuilder(search);

		return repository.findAll(specification.build(), pageable);
	}

	@Override
	public boolean isPromotionExistsById(short id) {
		return repository.existsById(id);
	}

	@Override
	public void createPromotion(PromotionFormForCreating form) {
		repository.save(form.toEntity());
	}

	@Override
	public Promotion getPromotionByID(short id) {
		return repository.findById(id).get();
	}

	@Override
	public void updatePromotion(short id, PromotionFormForUpdating form) {
		Promotion entity = repository.findById(id).get();
		entity.setCode(form.getCode());
		entity.setValue(form.getValue());
		repository.save(entity);
	}

	@Transactional
	public void deletePromotions(List<Short> ids) {
		repository.deleteByIdIn(ids);
	}
}
