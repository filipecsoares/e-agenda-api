package com.filipe.agenda.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.filipe.agenda.model.User;
import com.filipe.agenda.repository.UserRepository;

@SpringBootTest
class UserServiceImplTest {

	@Autowired
	private UserServiceImpl service;

	@MockBean
	private UserRepository repository;

	@Test
	void shouldSaveANewUser() {
		User newUser = new User("Valid name", "valid@email.com", "valid_password");

		when(repository.save(any(User.class))).thenReturn(newUser);
		User userCreated = service.create(newUser);
		verify(repository, times(1)).save(any(User.class));

		assertNotNull(userCreated);
		assertEquals(newUser.getEmail(), userCreated.getEmail());
	}

	@Test
	void shouldGetUserById() {
		User user = new User("Valid name", "valid@email.com", "valid_password");
		Long userId = 1L;
		Optional<User> userOptional = Optional.of(user);

		when(repository.findById(any(Long.class))).thenReturn(userOptional);
		Optional<User> optional = service.getOne(userId);
		verify(repository, times(1)).findById(any(Long.class));

		assertTrue(optional.isPresent());
		assertEquals(optional.get().getEmail(), user.getEmail());
	}

	@Test
	void shouldReturnAnEmptyOptionalIfUserNotExists() {
		Long userId = 1L;

		when(repository.findById(any(Long.class))).thenReturn(Optional.empty());
		Optional<User> optional = service.getOne(userId);
		verify(repository, times(1)).findById(any(Long.class));

		assertTrue(optional.isEmpty());
	}

	@Test
	void shouldFindUserByEmail() {
		String validEmail = "valid@email.com";
		User user = new User("Valid name", validEmail, "valid_password");
		Optional<User> userOptional = Optional.of(user);

		when(repository.findByEmail(any(String.class))).thenReturn(userOptional);
		Optional<User> optional = service.findByEmail(validEmail);
		verify(repository, times(1)).findByEmail(any(String.class));

		assertTrue(optional.isPresent());
		assertEquals(optional.get().getEmail(), user.getEmail());
	}
}
