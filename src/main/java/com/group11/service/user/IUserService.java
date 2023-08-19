package com.group11.service.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.group11.dto.brand.BrandFormForUpdating;
import com.group11.dto.user.UserFormForUpdating;
import com.group11.entity.Brand;
//import com.group11.dto.ChangePublicProfileDTO;
import com.group11.entity.User;

public interface IUserService extends UserDetailsService {
	
	Page<User> getAllUsers(Pageable pageable, String search);

	void createUser(User user);
	
	void updateUser(short id, UserFormForUpdating form);

	User findUserByEmail(String email);

	User findUserByUserName(String username);
	
	User getUserByID(short id);

	void activeUser(String token);

	void sendConfirmUserRegistrationViaEmail(String email);

	boolean existsUserByEmail(String email);

	boolean existsUserByUserName(String userName);

	void resetPasswordViaEmail(String email);

	void resetPassword(String token, String newPassword);

	void sendResetPasswordViaEmail(String email);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	void deleteUsers(List<Short> ids);
	
//	void changeUserProfile(String username, ChangePublicProfileDTO dto);

}
