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

	@Override
	public Appointment cancel(Long id) {
		Appointment appointment = repository.getOne(id);
		appointment.setStatus(Status.CANCELED);
		return appointment;
	}

	@Override
	public Appointment appointment(Appointment appointment) {
		appointment.setStatus(Status.OPEN);
		return repository.save(appointment);
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
