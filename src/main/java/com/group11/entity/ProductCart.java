package com.group11.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "`Product_Cart`")
public class ProductCart{
	@EmbeddedId
    private ProductCartId id;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cartId", insertable = false, updatable = false)
    private Cart cart;

    @Column(name = "quantity")
    private int quantity;
    
	public ProductCart(short productId, short cartId, int quantity) {
		this.id = new ProductCartId();
		this.id.setProductId(productId);
		this.id.setCartId(cartId);
		this.quantity = quantity;
		
	}
}
