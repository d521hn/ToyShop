package com.group11.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ProductOrderId implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Column(name = "productId")
    private short productId;

    @Column(name = "orderId")
    private short orderId;

}