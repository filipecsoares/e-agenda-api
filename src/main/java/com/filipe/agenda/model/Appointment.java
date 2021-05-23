package com.filipe.agenda.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Agenda agenda;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dtWhen;

	public Appointment() {
	}

	public Appointment(Agenda agenda, User user, LocalDateTime dtWhen) {
		super();
		this.agenda = agenda;
		this.user = user;
		this.dtWhen = dtWhen;
		this.status = Status.OPEN;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getDtWhen() {
		return dtWhen;
	}

	public void setDtWhen(LocalDateTime dtWhen) {
		this.dtWhen = dtWhen;
	}
}
