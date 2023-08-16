package com.group11.dto.product;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductFilter {
	private int minPrice;
	private int maxPrice;
	private List<String> brandNames;
	private String ageGroup;
}
