package com.filipe.agenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filipe.agenda.dto.UserDto;
import com.filipe.agenda.model.User;
import com.filipe.agenda.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDto getOne(Long userId) {
		User user = userRepository.getOne(userId);
		if (user != null) {
			return new UserDto(user);
		}
		return null;
	}

}
