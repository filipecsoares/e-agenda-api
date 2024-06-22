package com.filipe.agenda.config.security;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Optional;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.filipe.agenda.model.User;
import com.filipe.agenda.repository.UserRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

	private final TokenService tokenService;
	private final UserRepository userRepository;

	public AuthenticationTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain)
			throws ServletException, IOException {
		final String token = getToken(request);
		final boolean isValid = tokenService.isValidToken(token);
		if (isValid) {
			authenticateClient(token);
		}
		filterChain.doFilter(request, response);
	}

	private void authenticateClient(String token) {
		Long userId = tokenService.getUserId(token);
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isEmpty()) {
			throw new InvalidParameterException("Invalid token");
		}
		User user = userOptional.get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7);
	}
}
