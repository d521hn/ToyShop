package com.group11.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "`Cart`")
public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private short id;
	
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
	private User user;
	
	public void setUserId(short userId) {
		this.user = new User();
		this.user.setId(userId);
	}
	public Cart(short userId) {
		super();
		setUserId(userId);
	}
	
	
}
