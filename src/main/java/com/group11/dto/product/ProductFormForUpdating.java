package com.group11.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFormForUpdating {
	String name;
	int price;
	String describe;
	String info;
	String guide;
	String image;
	String status;
	short quantity;
}
