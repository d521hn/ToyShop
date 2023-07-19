package com.group11.entity;

import java.io.Serializable;


import javax.persistence.*;

import org.hibernate.annotations.Formula;

import lombok.Data;

@Data
@Entity
@Table(name = "`User`")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`id`")
	private short id;

	@Column(name = "`username`", nullable = false, length = 50, unique = true)
	private String userName;

	@Column(name = "`email`", nullable = false, length = 50, unique = true)
	private String email;

	@Column(name = "`password`", nullable = false, length = 800)
	private String password;

	@Column(name = "`firstName`", nullable = false, length = 50)
	private String firstName;

	@Column(name = "`lastName`", nullable = false, length = 50)
	private String lastName;

	@Formula("concat(firstName, ' ', lastName)")
	private String fullName;

	@Column(name = "`address`", length = 50)
	private String address;

	@Column(name = "`role`", columnDefinition = "ENUM('ADMIN', 'EMPLOYEE', 'CUSTOMER')")
	@Enumerated(EnumType.STRING)
	private Role role;

	public enum Role {
		ADMIN, EMPLOYEE, CUSTOMER;
		public static Role toEnum(String name) {
			for (Role item : Role.values()) {
				if (item.toString().equals(name))
					return item;
			}
			return null;
		}
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "`status`", nullable = false)
	private UserStatus status = UserStatus.NOT_ACTIVE;

	@PrePersist
	public void prePersist() {
		if (role == null) {
			role = Role.ADMIN;
		}
	}


	public User(String userName, String email, String password, String firstName, String lastName) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User() {
	}

	public String getRole() {
		return role.name();
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", fullName='" + fullName + '\'' +
				'}';
	}
}