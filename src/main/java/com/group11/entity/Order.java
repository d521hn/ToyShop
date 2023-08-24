package com.group11.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

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
    
	@OneToOne
	@JoinColumn(name = "shipId", referencedColumnName = "id", nullable = false)
	private Shipping ship;
    
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
	private User user;
	
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductOrder> productOrder;
	
    @Column(name = "`orderStatus`", length = 50)
    private String orderStatus;
    
    @Column(name = "`paymentStatus`", length = 50)
    private String paymentStatus;
    
	@Column(name = "createdTime")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdTime;
	
	@Column(name = "`paymentMethod`", columnDefinition = "ENUM('COD', 'BANKING', 'VNPAY')")
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	public enum PaymentMethod {
		COD, BANKING, VNPAY;
		public static PaymentMethod toEnum(String name) {
			for (PaymentMethod item : PaymentMethod.values()) {
				if (item.toString().equals(name))
					return item;
			}
			return null;
		}
	}
	
	public void setUserId(short userId) {
		this.user = new User();
		this.user.setId(userId);
	}

	public void setShipId(short shipId) {
		this.ship = new Shipping();
		this.ship.setId(shipId);
	}

	public Order(short shipId, short userId, String orderStatus, Date createdTime, PaymentMethod paymentMethod, String paymentStatus) {
		super();
		setUserId(userId);
		setShipId(shipId);
		this.orderStatus = orderStatus;
		this.createdTime = createdTime;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
	}

}
