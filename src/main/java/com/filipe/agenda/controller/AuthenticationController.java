package com.filipe.agenda.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filipe.agenda.config.security.TokenService;
import com.filipe.agenda.controller.form.LoginForm;
import com.filipe.agenda.dto.AuthenticationDto;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<AuthenticationDto> authenticate(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken login = form.converter();
		try {
			Authentication authentication = authManager.authenticate(login);
			String token = tokenService.generateToken(authentication);
			Long userId = tokenService.getUserId(token);
			return ResponseEntity.ok(new AuthenticationDto(token, "Bearer", userId, form.getEmail()));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
