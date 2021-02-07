package com.filipe.agenda.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeAdapter {

	public String encode(String text) {
		return new BCryptPasswordEncoder().encode(text);
	}

	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
