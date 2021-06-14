package com.filipe.agenda.config.email;

public interface EmailService {

	public void sendSimpleMessage(String to, String subject, String text);
}
