package com.group11.dto;

import com.group11.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

	private String userName;

	private String email;

	private String password;

	private String firstName;

	private String lastName;

	public User toEntity() {
		return new User(userName, email, password, firstName, lastName);
	}
}