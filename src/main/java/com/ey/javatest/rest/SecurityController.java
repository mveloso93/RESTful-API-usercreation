package com.ey.javatest.rest;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ey.javatest.data.dto.CreateUserRequestDTO;
import com.ey.javatest.data.dto.CreateUserResponseDTO;
import com.ey.javatest.service.LoginService;
import com.ey.javatest.service.SecurityService;

@RestController
public class SecurityController {

	@Autowired
	private LoginService logIn;
	
	@Autowired
	private SecurityService securityService;
	
	@PostMapping("/createuser")
	public ResponseEntity<CreateUserResponseDTO> register(@Valid @RequestBody CreateUserRequestDTO request) throws IOException {
		
		CreateUserResponseDTO response = securityService.registerUser(request);
		//login y generación de token
		String jwt = logIn.logInTokenGeneration(request.getEmail(), request.getPassword());
		
		//se actualiza response con el token
		response.getMensaje().setToken(jwt);
        
        //se actualiza usuario con el token
        securityService.addTokenToUser(response.getMensaje().getId(), jwt);
        
        return new ResponseEntity<CreateUserResponseDTO>(response, HttpStatus.CREATED);

	}
}
