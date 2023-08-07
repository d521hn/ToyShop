package com.group11.service.promotion;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.group11.dto.promotion.PromotionFormForCreating;
import com.group11.dto.promotion.PromotionFormForUpdating;
import com.group11.entity.Promotion;

public interface IPromotionService {
	Page<Promotion> getAllPromotions(Pageable pageable, String search);
	
	boolean isPromotionExistsById(short id);

	void createPromotion(PromotionFormForCreating form);

	Promotion getPromotionByID(short id);

	void updatePromotion(short id, PromotionFormForUpdating form);

	void deletePromotions(List<Short> ids);
}
