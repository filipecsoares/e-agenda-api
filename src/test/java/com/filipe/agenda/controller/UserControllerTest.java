package com.filipe.agenda.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipe.agenda.config.security.AuthenticationService;
import com.filipe.agenda.config.security.TokenService;
import com.filipe.agenda.model.User;
import com.filipe.agenda.repository.UserRepository;
import com.filipe.agenda.service.UserService;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UserService service;

	@MockBean
	private AuthenticationService authenticationService;

	@MockBean
	private TokenService tokenService;

	@MockBean
	private UserRepository userRepository;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		String validToken = "valid_token";
		User validUser = new User("Valid Name", "valid@email.com", "validPassword");
		Optional<User> userOptional = Optional.of(validUser);
		when(authenticationService.loadUserByUsername(any(String.class))).thenReturn(validUser);
		when(tokenService.generateToken(any(Authentication.class))).thenReturn(validToken);
		when(tokenService.isValidToken(any(String.class))).thenReturn(true);
		when(tokenService.isValidToken(isNull())).thenReturn(true);
		when(tokenService.getUserId(isNull())).thenReturn(1L);
		when(tokenService.getUserId(any(String.class))).thenReturn(1L);
		when(userRepository.findById(any(Long.class))).thenReturn(userOptional);
	}

	@Test
	public void shouldReturnOkStatusOnGetRequestWithNoParams() throws Exception {
		mockMvc.perform(get("/users")).andExpect(status().isOk());
	}

	@Test
	public void shouldReturnOkStatusOnGetRequestWithIdParam() throws Exception {
		Long userId = 1L;
		User returnedUser = new User("Valid Name", "valid@email.com", "validPassword");
		when(service.getOne(any(Long.class))).thenReturn(Optional.of(returnedUser));
		mockMvc.perform(get("/users/" + userId)).andExpect(status().isOk());
		verify(service, times(1)).getOne(any(Long.class));
	}

	@Test
	public void shouldReturnCreatedStatusOnPostRequest() throws Exception {
		User validUser = new User("Valid Name", "valid@email.com", "validPassword");
		User returnedUser = new User("Valid Name", "valid@email.com", "validPassword");
		when(service.create(any(User.class))).thenReturn(returnedUser);
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(validUser))).andExpect(status().isCreated());
		verify(service, times(1)).create(any(User.class));
	}

	@Test
	public void shouldReturnOkStatusOnPutRequest() throws Exception {
		Long userId = 1L;
		User validUser = new User("Valid Name", "valid@email.com", "validPassword");
		Optional<User> userOptional = Optional.of(validUser);
		when(service.getOne(any(Long.class))).thenReturn(userOptional);
		mockMvc.perform(put("/users/" + userId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(validUser))).andExpect(status().isOk());
		verify(service, times(1)).update(any(User.class));
	}

	@Test
	public void shouldReturnNotFoundStatusIfUserNotExists() throws Exception {
		Long userId = 1L;
		User validUser = new User("Valid Name", "valid@email.com", "validPassword");
		mockMvc.perform(put("/users/" + userId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(validUser))).andExpect(status().isNotFound());
	}
}
