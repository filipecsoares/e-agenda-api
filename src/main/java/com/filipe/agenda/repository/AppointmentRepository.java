package com.filipe.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filipe.agenda.model.Appointment;
import com.filipe.agenda.model.Status;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	public List<Appointment> findByStatus(Status status);
	
	public List<Appointment> findByStatusAndAgendaId(Status status, Long agendaId);
}
