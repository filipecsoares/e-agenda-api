package com.filipe.agenda.service;

import com.filipe.agenda.dto.UserDto;
import com.filipe.agenda.model.User;

public interface UserService {

	public User create(User user);

	public void update(User user);

	public UserDto getOne(Long userId);
}
