package com.filipe.agenda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filipe.agenda.model.Appointment;
import com.filipe.agenda.model.Status;
import com.filipe.agenda.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository repository;

	@Autowired
	private AppointmentEmailService appointmentEmailService;

	@Override
	public Appointment cancel(Long id) {
		Appointment appointment = repository.getOne(id);
		appointment.setStatus(Status.CANCELED);
		return appointment;
	}

	@Override
	public Appointment appointment(Appointment appointment) {
		appointment.setStatus(Status.OPEN);
		Appointment savedAppointment = repository.save(appointment);
		if (hasValidUser(savedAppointment)) {
			appointmentEmailService.sendEmailToClient(savedAppointment.getUser().getId(), savedAppointment.getDtWhen());
			appointmentEmailService.sendEmailToWorker(savedAppointment.getUser().getId(), savedAppointment.getDtWhen());
		}
		return savedAppointment;
	}

	private boolean hasValidUser(Appointment savedAppointment) {
		return savedAppointment != null && savedAppointment.getUser() != null
				&& savedAppointment.getUser().getId() != null;
	}

	@Override
	public List<Appointment> getAllOpen() {
		return repository.findByStatus(Status.OPEN);
	}

	@Override
	public Optional<Appointment> findById(Long appointmentId) {
		return repository.findById(appointmentId);
	}

	@Override
	public List<Appointment> getAllOpenByAgenda(Long agendaId) {
		return repository.findByStatusAndAgendaId(Status.OPEN, agendaId);
	}

}
