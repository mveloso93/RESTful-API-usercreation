package com.ey.javatest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ey.javatest.data.dto.JwtObject;
import com.ey.javatest.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
	
	boolean existsByEmail(String email);
	
	@Query(   "   SELECT new com.ey.javatest.data.dto.JwtObject(u.id, u.name , u.email, u.password ) " 
			+ "     FROM User u "
	        + "    WHERE u.email = :mail ")
	public JwtObject findByMail(@Param("mail") String mail);
	

}
