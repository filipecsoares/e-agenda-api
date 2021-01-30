package com.filipe.agenda.service;

import java.util.List;
import java.util.Optional;

import com.filipe.agenda.dto.UserDto;
import com.filipe.agenda.model.User;

public interface UserService {

	public User create(User user);

	public void update(User user);

	public Optional<User> getOne(Long userId);

	public List<UserDto> getAll();

	public Optional<User> findByEmail(String email);
}
