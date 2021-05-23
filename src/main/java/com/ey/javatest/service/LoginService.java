package com.ey.javatest.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ey.javatest.jwt.JwtTokenProvider;

@Service
public class LoginService {

	@Autowired
	public AuthenticationManager authenticationManager;

	@Autowired
	public JwtTokenProvider tokenProvider;

	public String logInTokenGeneration(String mail, String password) throws IOException {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(mail, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = "";
		token = tokenProvider.generateToken(authentication);
		return token;
	}

}
