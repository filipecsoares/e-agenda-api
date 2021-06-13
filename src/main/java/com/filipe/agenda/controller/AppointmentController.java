package com.filipe.agenda.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filipe.agenda.model.Appointment;
import com.filipe.agenda.service.AppointmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/appointments")
@Api(value = "Appointment API")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@ApiOperation(value = "Cadastrar um novo agendamento")
	@PostMapping
	public ResponseEntity<Appointment> save(@RequestBody @Valid Appointment appointment) {
		Appointment createdAppointment = appointmentService.appointment(appointment);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
	}

	@ApiOperation(value = "Exibe a lista de todos os agendamentos")
	@GetMapping
	public ResponseEntity<List<Appointment>> getAllOpen() {
		List<Appointment> appointments = appointmentService.getAllOpen();
		return ResponseEntity.status(HttpStatus.OK).body(appointments);
	}
	
	@ApiOperation(value = "Exibe a lista de todos os agendamentos de uma agenda")
	@GetMapping("/{agendaId}")
	public ResponseEntity<List<Appointment>> getAllOpenByAgenda(@PathVariable Long agendaId) {
		List<Appointment> appointments = appointmentService.getAllOpenByAgenda(agendaId);
		return ResponseEntity.status(HttpStatus.OK).body(appointments);
	}

	@ApiOperation(value = "Cancelar um agendamento por Id")
	@Transactional
	@DeleteMapping("/cancel/{id}")
	public ResponseEntity<Appointment> cancel(@PathVariable Long id) {
		Optional<Appointment> optional = appointmentService.findById(id);
		if (optional.isPresent()) {
			Appointment appointment = appointmentService.cancel(id);
			return ResponseEntity.ok(appointment);
		}
		return ResponseEntity.notFound().build();
	}
}
