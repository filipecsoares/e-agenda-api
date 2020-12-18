package com.filipe.agenda.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.filipe.agenda.model.User;

public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String phone;
	private Boolean active;
	private LocalDateTime createdAt;

	public UserDto(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.active = user.getActive();
		this.createdAt = user.getCreatedAt();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public Boolean getActive() {
		return active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public static List<UserDto> cast(List<User> users) {
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}

	public static UserDto cast(User user) {
		return new UserDto(user);
	}
}
