package com.filipe.agenda.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipe.agenda.model.User;
import com.filipe.agenda.service.UserService;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UserService service;

	@Test
	public void shouldReturnOkStatusOnGetRequestWithNoParams() throws Exception {
		mockMvc.perform(get("/users")).andExpect(status().isOk());
	}

	@Test
	public void shouldReturnCreatedStatusOnPostRequest() throws Exception {
		User validUser = new User("Valid Name", "valid@email.com", "validPassword");
		User returnedUser = new User("Valid Name", "valid@email.com", "validPassword");
		when(service.create(ArgumentMatchers.any(User.class))).thenReturn(returnedUser);
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(validUser))).andExpect(status().isCreated());
		verify(service, times(1)).create(ArgumentMatchers.any(User.class));
	}
}
