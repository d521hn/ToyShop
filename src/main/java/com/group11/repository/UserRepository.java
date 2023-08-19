package com.group11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group11.entity.User;
import com.group11.entity.UserStatus;

public interface UserRepository extends JpaRepository<User, Short>, JpaSpecificationExecutor<User> {

	public boolean existsByUserName(String userName);

	public boolean existsByEmail(String email);
	
	@Query("	SELECT 	status 		"
			+ "	FROM 	User 		"
			+ " WHERE 	email = :email")
	public UserStatus findStatusByEmail(@Param("email") String email);

	public User findByUserName(String name);
	
	public User findByEmail(String email);
	
	public void deleteByIdIn(List<Short> ids);
}
