package com.filipe.agenda.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.filipe.agenda.repository.UserRepository;
import com.filipe.agenda.utils.EncodeAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private final TokenService tokenService;
	private final UserRepository userRepository;

	private static final String[] AUTH_WHITELIST = {
			// -- Swagger UI v2
			"/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**" };

    public SecurityConfiguration(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(AUTH_WHITELIST).permitAll()
						.requestMatchers(HttpMethod.POST, "/auth").permitAll()
						.requestMatchers(HttpMethod.POST, "/users").permitAll()
						.requestMatchers(HttpMethod.GET, "/users/*").permitAll()
						.requestMatchers(HttpMethod.GET, "/users").permitAll()
						.anyRequest().authenticated())
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(new AuthenticationTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new EncodeAdapter().getPasswordEncoder();
	}
}
