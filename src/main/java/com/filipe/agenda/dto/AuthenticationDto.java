package com.filipe.agenda.dto;

public class AuthenticationDto {

	private String token;
	private String type;
	private Long userId;
	private String email;

	public AuthenticationDto(String token, String type, Long userId, String email) {
		this.token = token;
		this.type = type;
		this.userId = userId;
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public Long getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}
}