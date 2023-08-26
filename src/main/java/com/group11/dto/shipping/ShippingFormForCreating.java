package com.group11.dto.shipping;

import com.group11.entity.Shipping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingFormForCreating {
	String fullName;
	String email;
	String phoneNumber;
	String address;
	
	public Shipping toEntity() {
		return new Shipping (fullName, email, phoneNumber, address);
	}
}	
