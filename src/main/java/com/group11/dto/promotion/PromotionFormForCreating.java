package com.group11.dto.promotion;

import com.group11.entity.Promotion;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PromotionFormForCreating {
	String code;
	short value;
	
	public Promotion toEntity() { 
		return new Promotion(code, value);
	}
}
