package com.filipe.agenda.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

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
	
	private String description;

	public Appointment() {
	}

	public Appointment(Agenda agenda, User user, LocalDateTime dtWhen, String description) {
		super();
		this.agenda = agenda;
		this.user = user;
		this.dtWhen = dtWhen;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
