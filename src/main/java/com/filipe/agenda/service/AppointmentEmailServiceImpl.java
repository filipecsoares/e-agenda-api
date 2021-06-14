package com.filipe.agenda.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filipe.agenda.config.email.EmailService;
import com.filipe.agenda.model.User;

@Service
public class AppointmentEmailServiceImpl implements AppointmentEmailService {

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserService userService;

	@Override
	public void sendEmailToWorker(final Long userId, final LocalDateTime dtWhen) {
		Optional<User> user = userService.findById(userId);
		if (user.isPresent()) {
			StringBuilder emailContent = new StringBuilder();
			emailContent.append("Um novo agendamento foi marcado na sua agenda\n");
			emailContent.append("Dia e hora: " + dtWhen.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")) + "\n");
			emailContent.append("\n\nFique atento a sua agenda!");
			emailService.sendSimpleMessage(user.get().getEmail(), "Novo Agendamento marcado", emailContent.toString());
		}
	}

	@Override
	public void sendEmailToClient(final Long userId, final LocalDateTime dtWhen) {
		Optional<User> user = userService.findById(userId);
		if (user.isPresent()) {
			StringBuilder emailContent = new StringBuilder();
			emailContent.append("Você marcou um agendamento\n");
			emailContent.append("Dia e hora: " + dtWhen.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")) + "\n");
			emailContent.append("\n\nFique atento a sua agenda!");
			emailService.sendSimpleMessage(user.get().getEmail(), "Agendamento marcado", emailContent.toString());
		}
	}
}
