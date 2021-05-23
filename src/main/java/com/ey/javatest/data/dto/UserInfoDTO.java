package com.ey.javatest.data.dto;

import java.time.LocalDateTime;

public class UserInfoDTO {

	private String token;

	private String id;
	
	private String name;
	
	private String email;
	
	private LocalDateTime created;

	private LocalDateTime modified;

	private LocalDateTime last_login;

	private boolean active;

	public UserInfoDTO() {
		super();
	}

	public UserInfoDTO(String token, String id, LocalDateTime created, LocalDateTime modified, LocalDateTime last_login,
			boolean active) {
		super();
		this.token = token;
		this.id = id;
		this.created = created;
		this.modified = modified;
		this.last_login = last_login;
		this.active = active;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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


	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public LocalDateTime getLast_login() {
		return last_login;
	}

	public void setLast_login(LocalDateTime last_login) {
		this.last_login = last_login;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
