package com.group11.service.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.group11.dto.brand.BrandFormForUpdating;
import com.group11.dto.user.UserFormForUpdating;
import com.group11.entity.Brand;
//import com.group11.dto.ChangePublicProfileDTO;
import com.group11.entity.RegistrationUserToken;
import com.group11.entity.ResetPasswordToken;
import com.group11.entity.User;
import com.group11.entity.UserStatus;
import com.group11.event.OnResetPasswordViaEmailEvent;
import com.group11.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.group11.repository.RegistrationUserTokenRepository;
import com.group11.repository.ResetPasswordTokenRepository;
import com.group11.repository.UserRepository;
import com.group11.specification.brand.BrandSpecificationBuilder;
import com.group11.specification.user.UserSpecificationBuilder;

@Component
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RegistrationUserTokenRepository registrationUserTokenRepository;

	@Autowired
	private ResetPasswordTokenRepository resetPasswordTokenRepository;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Page<User> getAllUsers(Pageable pageable, String search) {
		UserSpecificationBuilder specification = new UserSpecificationBuilder(search);

		return userRepository.findAll(specification.build(), pageable);
	}

	@Override
	public void createUser(User user) {

		// encode password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// create user
		userRepository.save(user);

		// create new user registration token
		createNewRegistrationUserToken(user);

		//lỗi ở phần sendConfirmEmail
		// send email to confirm
		sendConfirmUserRegistrationViaEmail(user.getEmail());
	}
	
	@Override
	public User getUserByID(short id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public void updateUser(short id, UserFormForUpdating form) {
		User entity = userRepository.findById(id).get();
//		entity.setUserName(form.getUserName());
//		entity.setEmail(form.getEmail());
//		entity.setFirstName(form.getFirstName());
//		entity.setLastName(form.getLastName());
		entity.setRole(form.getRole());
		userRepository.save(entity);
	}

	private void createNewRegistrationUserToken(User user) {

		// create new token for confirm Registration
		final String newToken = UUID.randomUUID().toString();
		RegistrationUserToken token = new RegistrationUserToken(newToken, user);

		registrationUserTokenRepository.save(token);
	}

	@Override
	public void sendConfirmUserRegistrationViaEmail(String email) {
		eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	@Override
	public boolean existsUserByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public boolean existsUserByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	@Override
	public void activeUser(String token) {

		// get token
		RegistrationUserToken registrationUserToken = registrationUserTokenRepository.findByToken(token);

		// active user
		User user = registrationUserToken.getUser();
		user.setStatus(UserStatus.ACTIVE);
		userRepository.save(user);

		// remove Registration User Token
		registrationUserTokenRepository.deleteById(registrationUserToken.getId());
	}

	@Override
	public void resetPasswordViaEmail(String email) {

		// find user by email
		User user = findUserByEmail(email);

		// remove token token if exists
		resetPasswordTokenRepository.deleteByUserId(user.getId());

		// create new reset password token
		createNewResetPasswordToken(user);

		// send email
		sendResetPasswordViaEmail(email);
	}

	@Override
	public void sendResetPasswordViaEmail(String email) {
		eventPublisher.publishEvent(new OnResetPasswordViaEmailEvent(email));
	}

	private void createNewResetPasswordToken(User user) {

		// create new token for Reseting password
		final String newToken = UUID.randomUUID().toString();
		ResetPasswordToken token = new ResetPasswordToken(newToken, user);

		resetPasswordTokenRepository.save(token);
	}

	@Override
	public void resetPassword(String token, String newPassword) {
		// get token
		ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(token);

		// change password
		User user = resetPasswordToken.getUser();
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);

		// remove Reset Password
		resetPasswordTokenRepository.deleteById(resetPasswordToken.getId());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Check user exists by username
		User user = userRepository.findByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRole()));
	}
	
	@Transactional
	public void deleteUsers(List<Short> ids) {
		userRepository.deleteByIdIn(ids);
	}

//	@Override
//	public void changeUserProfile(String username, ChangePublicProfileDTO dto) {
//		User user = userRepository.findByUserName(username);
//		
//		user.setAvatar(dto.getAvatarUrl());
//		userRepository.save(user);
//		
//		// TODO other field
//	}

}
