package com.filipe.agenda.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	private String address;

	private String daysOfWeek;

	public Agenda() {
	}

	public Agenda(User user, LocalTime fromHour, LocalTime toHour, LocalTime serviceTime, LocalTime lunchBreakFrom,
			LocalTime lunchBreakTo, String daysOfWeek, String address) {
		super();
		this.user = user;
		this.fromHour = fromHour;
		this.toHour = toHour;
		this.serviceTime = serviceTime;
		this.lunchBreakFrom = lunchBreakFrom;
		this.lunchBreakTo = lunchBreakTo;
		this.daysOfWeek = daysOfWeek;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
