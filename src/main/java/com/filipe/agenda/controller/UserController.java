package com.filipe.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.filipe.agenda.dto.UserDto;
import com.filipe.agenda.model.User;
import com.filipe.agenda.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok("It's Ok");
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
		UserDto user = userService.getOne(userId);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User createdUser = userService.create(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody User user) {
		userService.update(user);
	}
}
