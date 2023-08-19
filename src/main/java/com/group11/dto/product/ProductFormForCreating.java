package com.group11.dto.product;

import com.group11.entity.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFormForCreating {
	String name;
	int price;
	String describe;
	String info;
	String guide;
	String image;
	String status;
	String ageGroup;
	short quantity;
	short cateId;
	short brandId;
	
	public Product toEntity() {
		return new Product (name, price, describe, info, guide, image, status, quantity, ageGroup, cateId, brandId);
	}
}
