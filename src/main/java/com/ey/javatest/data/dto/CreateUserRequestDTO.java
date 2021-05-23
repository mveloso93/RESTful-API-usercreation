package com.ey.javatest.data.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ey.javatest.validations.ValidEmail;


public class CreateUserRequestDTO {

	@NotEmpty
	@NotNull
	private String name;
	
	@ValidEmail
	@Pattern(message ="No cumple un formato válido", regexp = "[-a-zA-Z0-9!#$%&'*+/=?^_'{|}~]+(?:\\.[-a-zA-Z0-9!#$%&'*+/=?^_'{|}~]+)*@([a-zA-Z0-9](?:[-a-zA-Z0-9]*[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[-a-zA-Z0-9]*[a-zA-Z0-9])?)*(?:\\.[a-zA-Z0-9]{2,}+)|\\[(?:\\d{1,3}(?:\\.\\d{1,3}){3}|IPv6:[0-9A-Fa-f:]{4,39})\\])")
	@NotEmpty
	private String email;

	@Pattern(message = "Debe contener una mayúscula, letras minúsculas y 2 números", regexp = "^(?=(?:.*[A-Z]){1})(?=(?:.*[a-z]))(?=(?:.*[0-9]){2})\\S{1,}$")
	@NotEmpty
	@NotNull
	private String password;

	@NotEmpty
	@NotNull
	private List<ContactDTO> phones = new ArrayList<>();

	public CreateUserRequestDTO(String name, String email, String password, List<ContactDTO> phones) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = phones;
	}

	public CreateUserRequestDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ContactDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<ContactDTO> phones) {
		this.phones = phones;
	}

}
