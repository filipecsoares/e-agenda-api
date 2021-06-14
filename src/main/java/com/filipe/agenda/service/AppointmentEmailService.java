package com.filipe.agenda.service;

import java.time.LocalDateTime;

public interface AppointmentEmailService {

	public void sendEmailToWorker(final Long userId, final LocalDateTime dtWhen);

	public void sendEmailToClient(final Long userId, final LocalDateTime dtWhen);
}
