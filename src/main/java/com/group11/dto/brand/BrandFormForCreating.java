package com.group11.dto.brand;

import com.group11.entity.Brand;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BrandFormForCreating {
	String name;
	
	public Brand toEntity() {
		return new Brand (name);
	}
}
