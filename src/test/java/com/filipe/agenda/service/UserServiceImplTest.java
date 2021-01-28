package com.filipe.agenda.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.filipe.agenda.dto.UserDto;
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

		when(repository.getOne(any(Long.class))).thenReturn(user);
		UserDto userDto = service.getOne(userId);
		verify(repository, times(1)).getOne(any(Long.class));

		assertNotNull(userDto);
		assertEquals(userDto.getEmail(), user.getEmail());
	}

	@Test
	void shouldReturnAnEmptyObjectIfUserNotExists() {
		Long userId = 1L;

		when(repository.getOne(any(Long.class))).thenReturn(null);
		UserDto userDto = service.getOne(userId);
		verify(repository, times(1)).getOne(any(Long.class));

		assertNotNull(userDto);
		assertNull(userDto.getId());
		assertNull(userDto.getEmail());
	}

	@Test
	void shouldFindUserByEmail() {
		String validEmail = "valid@email.com";
		User user = new User("Valid name", validEmail, "valid_password");
		Optional<User> userOptional = Optional.of(user);

		when(repository.findByEmail(any(String.class))).thenReturn(userOptional);
		UserDto userDto = service.findByEmail(validEmail);
		verify(repository, times(1)).findByEmail(any(String.class));

		assertNotNull(userDto);
		assertEquals(userDto.getEmail(), user.getEmail());
	}
}
