package com.filipe.agenda.service;

import java.util.List;
import java.util.Optional;

import com.filipe.agenda.model.Appointment;

public interface AppointmentService {

	public Appointment cancel(final Long id);

	public Appointment appointment(Appointment appointment);

	public List<Appointment> getAllOpen();

	public Optional<Appointment> findById(Long appointmentId);
}
