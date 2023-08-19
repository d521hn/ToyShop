package com.group11.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.group11.dto.ChangePublicProfileDTO;
//import com.group11.dto.ProfileDTO;
import com.group11.dto.UserDTO;
import com.group11.dto.brand.BrandFormForUpdating;
import com.group11.dto.user.UserFormForUpdating;
import com.group11.entity.User;
import com.group11.entity.User;
import com.group11.service.user.IUserService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/users")
@Validated
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping()
	public ResponseEntity<?> getAllUsers(
			Pageable pageable,
			@RequestParam(required = false)
			String search) {
		Page<User> entities = userService.getAllUsers(pageable, search);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getUserByID(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(userService.getUserByID(id), HttpStatus.OK);
	}


	@GetMapping(value = "/email/{email}")
	public ResponseEntity<?> existsUserByEmail(@PathVariable(name = "email") String email) {
		// get entity
		boolean result = userService.existsUserByEmail(email);

		// return result
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/userName/{userName}")
	public ResponseEntity<?> existsUserByUserName(@PathVariable(name = "userName") String userName) {
		// get entity
		boolean result = userService.existsUserByUserName(userName);

		// return result
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/findByEmail/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable(name = "email") String email) {
		// get entity
		User result = userService.findUserByEmail(email);

		// return result
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(name = "id") short id, @RequestBody UserFormForUpdating form) {
		userService.updateUser(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}


	@PostMapping()
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO dto) {
		// create User
		userService.createUser(dto.toEntity());

		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}

	@GetMapping("/activeUser")
	// validate: check exists, check not expired
	public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {
		// active user
		userService.activeUser(token);

		return new ResponseEntity<>("Active success!", HttpStatus.OK);
	}

	// resend confirm
	@GetMapping("/userRegistrationConfirmRequest")
	// validate: email exists, email not active
	public ResponseEntity<?> resendConfirmRegistrationViaEmail(@RequestParam String email) {

		userService.sendConfirmUserRegistrationViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}

	// reset password confirm
	@GetMapping("/resetPasswordRequest")
	// validate: email exists, email not active
	public ResponseEntity<?> sendResetPasswordViaEmail(@RequestParam String email) {

		userService.resetPasswordViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
	}

	// resend reset password
	@GetMapping("/resendResetPassword")
	// validate: email exists, email not active
	public ResponseEntity<?> resendResetPasswordViaEmail(@RequestParam String email) {

		userService.sendResetPasswordViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
	}

	@GetMapping("/resetPassword")
	// validate: check exists, check not expired
	public ResponseEntity<?> resetPasswordViaEmail(@RequestParam String token, @RequestParam String newPassword) {

		// reset password
		userService.resetPassword(token, newPassword);

		return new ResponseEntity<>("Reset Password success!", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deleteUsers(@PathVariable(name = "ids") List<Short> ids) {
		userService.deleteUsers(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}

//	@GetMapping("/profile")
//	// validate: check exists, check not expired
//	public ResponseEntity<?> getUserProfile(Authentication authentication) {
//
//		// get username from token
//		String username = authentication.getName();
//
//		// get user info
//		User user = userService.findUserByUserName(username);
//
//		// convert user entity to user dto
//		ProfileDTO profileDto = new ProfileDTO(
//				user.getUserName(),
//				user.getEmail(),
//				user.getFirstName(),
//				user.getLastName(),
//				user.getRole(),
//				user.getStatus().toString());
//
//		return new ResponseEntity<>(profileDto, HttpStatus.OK);
//	}
//
//	@PutMapping("/profile")
//	// validate: check exists, check not expired
//	public ResponseEntity<?> changeUserProfile(Authentication authentication, @RequestBody ChangePublicProfileDTO dto) {
//
//		// get username from token
//		String username = authentication.getName();
//
//		userService.changeUserProfile(username, dto);
//
//		return new ResponseEntity<>("Change Profile Successfully!", HttpStatus.OK);
//	}

}
