package com.group11.specification.product;

import java.util.List;
import java.util.stream.Collectors;

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

	public Specification<Product> build() {
	    Specification<Product> where = Specification.where(null);

	    if (!StringUtils.isEmpty(search)) {
	        SearchCriteria searchName = new SearchCriteria("name", "searchByName", search);
	        where = where.and(new ProductSpecification(searchName));
	    }

	    if (filter.getBrandNames() != null && !filter.getBrandNames().isEmpty()) {
	        List<SearchCriteria> brandCriterias = filter.getBrandNames().stream()
	                .map(brandName -> new SearchCriteria("name", "filterByBrand", brandName))
	                .collect(Collectors.toList());

	        for (SearchCriteria brandCriteria : brandCriterias) {
	            where = where.or(new ProductSpecification(brandCriteria));
	        }
	    }
	    
	    if (filter.getCate() != null) {
	        SearchCriteria cate = new SearchCriteria("name", "filterByCate", filter.getCate());
	        where = where.and(new ProductSpecification(cate));
	    }

	    if (filter.getAgeGroup() != null) {
	        SearchCriteria ageGroup = new SearchCriteria("ageGroup", "filterByAgeGroup", filter.getAgeGroup());
	        where = where.and(new ProductSpecification(ageGroup));
	    }

	    if (filter.getMinPrice() != 0) {
	        SearchCriteria minPrice = new SearchCriteria("price", "filterMinPrice", filter.getMinPrice());
	        where = where.and(new ProductSpecification(minPrice));
	    }

	    if (filter.getMaxPrice() != 0) {
	        SearchCriteria maxPrice = new SearchCriteria("price", "filterMaxPrice", filter.getMaxPrice());
	        where = where.and(new ProductSpecification(maxPrice));
	    }

	    return where;
	}


}
