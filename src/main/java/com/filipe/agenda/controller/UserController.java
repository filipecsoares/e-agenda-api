package com.filipe.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping
	public ResponseEntity<User> save(User user) {
		User createdUser = userService.create(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
}
