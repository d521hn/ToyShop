package com.group11.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "`Order`")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private short id;
	
    @Column(name = "`orderStatus`", length = 50)
    private String orderStatus;
    
    @Column(name = "`deliveryAddress`", length = 50)
    private String deliAddress;
    
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
	private User user;

	public Order(String orderStatus, String deliAddress, short userId) {
		super();
		this.orderStatus = orderStatus;
		this.deliAddress = deliAddress;
		this.user = new User();
		this.user.setId(userId);
	}
	
	public void setUserId(short userId) {
		this.user = new User();
		this.user.setId(userId);
	}

}
