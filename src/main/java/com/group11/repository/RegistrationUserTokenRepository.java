package com.group11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.group11.entity.RegistrationUserToken;


public interface RegistrationUserTokenRepository extends JpaRepository<RegistrationUserToken, Short> {

	public RegistrationUserToken findByToken(String token);

	public boolean existsByToken(String token);
	
	@Query("	SELECT 	token	"
			+ "	FROM 	RegistrationUserToken "
			+ " WHERE 	user.id = :userId")
	public String findByUserId(@Param("userId") short userId);

	@Transactional
	@Modifying
	@Query("	DELETE 							"
			+ "	FROM 	RegistrationUserToken 	"
			+ " WHERE 	user.id = :userId")
	public void deleteByUserId(@Param("userId") short userId);

}
