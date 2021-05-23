package com.ey.javatest.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ey.javatest.data.dto.JwtObject;
import com.ey.javatest.repository.UserRepository;
public class EmailValidator implements ConstraintValidator<ValidEmail, String>{
    

	@Autowired
	private UserRepository userRepository;	
	
	@Override
      public boolean isValid(String email, ConstraintValidatorContext context) {
		JwtObject user = userRepository.findByMail(email);
		return user == null ? true : false;
      }
}