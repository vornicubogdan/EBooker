package com.app.eBooker.model;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Reservation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private ZonedDateTime startPeriod;
	@NotNull
	private ZonedDateTime endPeriod;
	@NotBlank
	private String customerName;
	@NotBlank
	private String location;
	@NotBlank
	private String carRegistrationNumber;
	
	private String comments;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ZonedDateTime getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(ZonedDateTime startPeriod) {
		this.startPeriod = startPeriod;
	}

	public ZonedDateTime getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(ZonedDateTime endPeriod) {
		this.endPeriod = endPeriod;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCarRegistrationNumber() {
		return carRegistrationNumber;
	}

	public void setCarRegistrationNumber(String carRegistrationNumber) {
		this.carRegistrationNumber = carRegistrationNumber;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	

}