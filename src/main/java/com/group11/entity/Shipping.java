package com.group11.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.group11.entity.User.Role;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "`Shipping`")
public class Shipping implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private short id;
	
    @Column(name = "`fullName`", nullable = false, length = 100)
    private String fullName;
    
    @Column(name = "`email`", nullable = false, length = 100, unique = true)
    private String email;
    
    @Column(name = "`phoneNumber`", nullable = false, length = 12, unique = true)
    private String phoneNumber;
    
    @Column(name = "`address`", nullable = false, length = 100)
    private String address;

}
