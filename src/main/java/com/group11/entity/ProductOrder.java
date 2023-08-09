package com.group11.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "`Product_Order`")
public class ProductOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private ProductOrderId id;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private Order order;

    @Column(name = "quantity")
    private int quantity;
    
	@Column(name = "`price`", nullable = false)
	private int price;

	public ProductOrder(short productId, short orderId, int quantity, int price) {
		this.id =  new ProductOrderId();
		this.id.setProductId(productId);
		this.id.setOrderId(orderId);
		this.quantity = quantity;
		this.price  = price;
	}
	
}
