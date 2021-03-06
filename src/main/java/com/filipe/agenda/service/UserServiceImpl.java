package com.filipe.agenda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.filipe.agenda.controller.form.UserUpdateForm;
import com.filipe.agenda.dto.UserDto;
import com.filipe.agenda.model.User;
import com.filipe.agenda.repository.UserRepository;
import com.filipe.agenda.utils.EncodeAdapter;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(User user) {
		user.setPassword(new EncodeAdapter().encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User getOne(Long userId) {
		return userRepository.getOne(userId);
	}

	@Override
	public List<UserDto> getAll() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User update(Long id, UserUpdateForm userForm) {
		User user = getOne(id);
		user.setName(userForm.getName());
		if (StringUtils.hasText(userForm.getPassword())) {
			user.setPassword(new EncodeAdapter().encode(userForm.getPassword()));
		}
		user.setPhone(userForm.getPhone());
		return user;
	}
}
