package com.group11.service.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group11.dto.shipping.ShippingFormForCreating;
import com.group11.entity.Shipping;
import com.group11.repository.IShippingRepository;

@Service
public class ShippingService implements IShippingService {
	@Autowired 
	private IShippingRepository repository;
	
	@Override
	public void createShipping(ShippingFormForCreating form) {
		repository.save(form.toEntity());
	}
	
	@Override
	public Shipping getById(short id) {
		return repository.findById(id);
	}
	
	@Override
	public Shipping getByEmail(String email) {
		return repository.findByEmail(email);
	}
}
