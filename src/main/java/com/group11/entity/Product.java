package com.group11.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`Product`")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private short id;
	
    @Column(name = "`name`", nullable = false, length = 200)
    private String name;
    
	@Column(name = "`price`", nullable = false)
	private int price;
	
	@Column(name = "`describe`", columnDefinition = "TEXT")
	private String describe;
	
	@Column(name = "`info`", columnDefinition = "TEXT")
	private String info;
	
	@Column(name = "`guide`", columnDefinition = "TEXT")
	private String guide;
	
    @Column(name = "`img`", length = 200)
    private String image;
    
    @Column(name = "`status`", length = 50)
    private String status;
    
	@Column(name = "`quantity`", nullable = false)
	private short quantity;
	
	@ManyToOne
	@JoinColumn(name = "cateId", referencedColumnName = "id", nullable = false)
	private Category cate;
	
	@ManyToOne
	@JoinColumn(name = "brandId", referencedColumnName = "id", nullable = false)
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "promotionId", referencedColumnName = "id", nullable = true)
	private Promotion promotion;

	public Product(String name, int price, String describe, String info, String guide, String image, String status,
			short quantity) {
		super();
		this.name = name;
		this.price = price;
		this.describe = describe;
		this.info = info;
		this.guide = guide;
		this.image = image;
		this.status = status;
		this.quantity = quantity;
	}
	
}
