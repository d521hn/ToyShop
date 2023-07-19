package com.group11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group11.dto.product.ProductFormForCreating;
import com.group11.dto.product.ProductFormForUpdating;
import com.group11.entity.Product;
import com.group11.repository.IProductRepository;
import com.group11.specification.ProductSpecificationBuilder;

@Service
public class ProductService implements IProductService {
	@Autowired 
	private IProductRepository repository;
	
	@Override
	public Page<Product> getAllProducts(Pageable pageable, String search) {
		ProductSpecificationBuilder specification = new ProductSpecificationBuilder(search);

		return repository.findAll(specification.build(), pageable);
	}

	@Override
	public boolean isProductExistsById(short id) {
		return repository.existsById(id);
	}

	@Override
	public void createProduct(ProductFormForCreating form) {
		repository.save(form.toEntity());
	}

	@Override
	public Product getProductByID(short id) {
		return repository.findById(id).get();
	}

	@Override
	public void updateProduct(short id, ProductFormForUpdating form) {
		Product entity = repository.findById(id).get();
		entity.setName(form.getName());
		entity.setDescribe(form.getDescribe());
		entity.setInfo(form.getInfo());
		entity.setGuide(form.getGuide());
		entity.setPrice(form.getPrice());
		entity.setImage(form.getImage());
		entity.setStatus(form.getStatus());
		entity.setQuantity(form.getQuantity());
		repository.save(entity);
	}

	@Transactional
	public void deleteProducts(List<Short> ids) {
		repository.deleteByIdIn(ids);
	}

}
