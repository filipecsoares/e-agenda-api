package com.filipe.agenda.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filipe.agenda.controller.form.UserUpdateForm;
import com.filipe.agenda.dto.UserDto;
import com.filipe.agenda.model.User;
import com.filipe.agenda.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(value = "User API")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Exibe a lista de usuários")
	@GetMapping
	public ResponseEntity<List<UserDto>> getAll() {
		List<UserDto> users = userService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@ApiOperation(value = "Consulta usuário por ID")
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
		Optional<User> optionalUser = userService.findById(userId);
		if (optionalUser.isPresent()) {
			return ResponseEntity.ok(new UserDto(optionalUser.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cadastrar um novo usuário")
	@PostMapping
	public ResponseEntity<UserDto> save(@RequestBody @Valid User user) {
		if (userService.findByEmail(user.getEmail()).isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		User createdUser = userService.create(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(UserDto.cast(createdUser));
	}

	@ApiOperation(value = "Atualizar um usuário")
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserUpdateForm userForm) {
		Optional<User> optional = userService.findById(id);
		if (optional.isPresent()) {
			User user = userService.update(id, userForm);
			return ResponseEntity.ok(new UserDto(user));
		}
		return ResponseEntity.notFound().build();
	}
}
