package com.group11.service.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.group11.dto.product.ProductFilter;
import com.group11.dto.product.ProductFormForCreating;
import com.group11.dto.product.ProductFormForUpdating;
import com.group11.entity.Product;

public interface IProductService {
	Page<Product> getAllProducts(Pageable pageable, ProductFilter filter, String search);
	
	boolean isProductExistsById(short id);

	void createProduct(ProductFormForCreating form);

	Product getProductByID(short id);

	void updateProduct(short id, ProductFormForUpdating form);

	void deleteProducts(List<Short> ids);
}
