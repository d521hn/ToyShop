package com.group11.service.brand;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.group11.dto.brand.BrandFormForCreating;
import com.group11.dto.brand.BrandFormForUpdating;
import com.group11.entity.Brand;

public interface IBrandService {
	Page<Brand> getAllBrands(Pageable pageable, String search);
	
	boolean isBrandExistsById(short id);
	
	boolean isBrandExistsByName(String name);

	void createBrand(BrandFormForCreating form);

	Brand getBrandByID(short id);

	void updateBrand(short id, BrandFormForUpdating form);

	void deleteBrands(List<Short> ids);
}
