package com.filipe.agenda.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@Column(columnDefinition = "TIME")
	private LocalTime fromHour;

	@Column(columnDefinition = "TIME")
	private LocalTime toHour;

	@Column(columnDefinition = "TIME")
	private LocalTime serviceTime;

	@Column(columnDefinition = "TIME")
	private LocalTime lunchBreakFrom;

	@Column(columnDefinition = "TIME")
	private LocalTime lunchBreakTo;

	private String name;

	private String address;

	private String daysOfWeek;

	public Agenda() {
	}

	public Agenda(User user, LocalTime fromHour, LocalTime toHour, LocalTime serviceTime, LocalTime lunchBreakFrom,
			LocalTime lunchBreakTo, String daysOfWeek, String name, String address) {
		super();
		this.user = user;
		this.fromHour = fromHour;
		this.toHour = toHour;
		this.serviceTime = serviceTime;
		this.lunchBreakFrom = lunchBreakFrom;
		this.lunchBreakTo = lunchBreakTo;
		this.daysOfWeek = daysOfWeek;
		this.name = name;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalTime getFromHour() {
		return fromHour;
	}

	public void setFromHour(LocalTime fromHour) {
		this.fromHour = fromHour;
	}

	public LocalTime getToHour() {
		return toHour;
	}

	public void setToHour(LocalTime toHour) {
		this.toHour = toHour;
	}

	public LocalTime getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(LocalTime serviceTime) {
		this.serviceTime = serviceTime;
	}

	public LocalTime getLunchBreakFrom() {
		return lunchBreakFrom;
	}

	public void setLunchBreakFrom(LocalTime lunchBreakFrom) {
		this.lunchBreakFrom = lunchBreakFrom;
	}

	public LocalTime getLunchBreakTo() {
		return lunchBreakTo;
	}

	public void setLunchBreakTo(LocalTime lunchBreakTo) {
		this.lunchBreakTo = lunchBreakTo;
	}

	public String getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
