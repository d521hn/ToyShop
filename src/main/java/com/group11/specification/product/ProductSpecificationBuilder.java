package com.group11.specification.product;

import org.springframework.data.jpa.domain.Specification;

import com.group11.dto.product.ProductFilter;
import com.group11.entity.Product;
import com.group11.specification.SearchCriteria;

import org.springframework.util.StringUtils;

public class ProductSpecificationBuilder {
	private ProductFilter filter;
	private String search;

	public ProductSpecificationBuilder(ProductFilter filter, String search) {
		this.filter = filter;
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Product> build() {

		SearchCriteria searchName = new SearchCriteria("name", "searchByName", search);
		SearchCriteria brandName = new SearchCriteria("name", "filterByBrand", filter.getBrandName());
		SearchCriteria minPrice = new SearchCriteria("price", "filterMinPrice", filter.getMinPrice());
		SearchCriteria maxPrice = new SearchCriteria("price", "filterMaxPrice", filter.getMaxPrice());

		Specification<Product> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new ProductSpecification(searchName);
		}
		
		// filter brandName
		if (filter.getBrandName() != null) {
			where = new ProductSpecification(brandName);
		}
		
		
		// min price filter
		if (filter.getMinPrice() != 0) {
			if (where != null) {
				where = where.and(new ProductSpecification(minPrice));
			} else {
				where = new ProductSpecification(minPrice);
			}
		}

		// max price filter
		if (filter.getMaxPrice() != 0) {
			if (where != null) {
				where = where.and(new ProductSpecification(maxPrice));
			} else {
				where = new ProductSpecification(maxPrice);
			}
		}

		return where;
	}
}
