package com.ey.javatest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.javatest.data.dto.ContactDTO;
import com.ey.javatest.data.dto.CreateUserRequestDTO;
import com.ey.javatest.data.dto.UserInfoDTO;
import com.ey.javatest.data.dto.JwtObject;
import com.ey.javatest.data.dto.CreateUserResponseDTO;
import com.ey.javatest.data.model.Phone;
import com.ey.javatest.data.model.User;
import com.ey.javatest.repository.PhoneRepository;
import com.ey.javatest.repository.UserRepository;

@Service
public class SecurityService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		JwtObject result =  userRepository.findByMail(email);
		List<String> listadoPermisos = new ArrayList<>();
		listadoPermisos.add("Admin");
		List<GrantedAuthority> authorities = listadoPermisos.stream().map(o -> new SimpleGrantedAuthority(o.toString())).collect(Collectors.toList());
		result.setAuthorities(authorities);
		return result;
	}
	
	public CreateUserResponseDTO registerUser(CreateUserRequestDTO request) throws IOException {
		
		CreateUserResponseDTO response = new CreateUserResponseDTO();
		UserInfoDTO result = new UserInfoDTO();
		
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		User user = new User(request.getName(), request.getEmail(), encodedPassword);
		user = userRepository.save(user);
		
		List<Phone> phonesList = new ArrayList<Phone>();
		for (ContactDTO item : request.getPhones()) {
			phonesList.add(new Phone(item.getNumber(), item.getCitycode(), item.getCountrycode(), user));
		}
		phoneRepository.saveAll(phonesList);
		
		result.setName(user.getName());
		result.setEmail(user.getEmail());
		result.setActive(user.isEnable());
		result.setCreated(user.getCreated());
		result.setId(user.getId().toString());
		result.setLast_login(user.getLast_login());
		result.setModified(user.getModified());
		
		response.setMensaje(result);
		
		return response;
	}
	
	public void addTokenToUser(String id, String token) {
		
		User user = userRepository.getById(UUID.fromString(id));
		user.setToken(token);
		user = userRepository.save(user);
	}

	
	
}
