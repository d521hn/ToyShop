package com.group11.service.shipping;

import com.group11.dto.shipping.ShippingFormForCreating;
import com.group11.entity.Shipping;

public interface IShippingService {
	
	void createShipping(ShippingFormForCreating form);
	
	Shipping getByEmail(String email);
	
	Shipping getById(short id);
}
