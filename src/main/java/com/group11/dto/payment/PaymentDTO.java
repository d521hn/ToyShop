package com.group11.dto.payment;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO implements Serializable{
	private String status;
	private String message;
	private String url;
	
	
}
