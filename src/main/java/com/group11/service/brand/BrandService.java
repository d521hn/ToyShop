package com.group11.service.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group11.dto.brand.BrandFormForCreating;
import com.group11.dto.brand.BrandFormForUpdating;
import com.group11.entity.Brand;
import com.group11.repository.IBrandRepository;
import com.group11.specification.brand.BrandSpecificationBuilder;

@Service
public class BrandService implements IBrandService {
	@Autowired 
	private IBrandRepository repository;
	
	@Override
	public Page<Brand> getAllBrands(Pageable pageable, String search) {
		BrandSpecificationBuilder specification = new BrandSpecificationBuilder(search);

		return repository.findAll(specification.build(), pageable);
	}

	@Override
	public boolean isBrandExistsById(short id) {
		return repository.existsById(id);
	}

	@Override
	public void createBrand(BrandFormForCreating form) {
		repository.save(form.toEntity());
	}

	@Override
	public Brand getBrandByID(short id) {
		return repository.findById(id).get();
	}

	@Override
	public void updateBrand(short id, BrandFormForUpdating form) {
		Brand entity = repository.findById(id).get();
		entity.setName(form.getName());
		repository.save(entity);
	}

	@Transactional
	public void deleteBrands(List<Short> ids) {
		repository.deleteByIdIn(ids);
	}
}
