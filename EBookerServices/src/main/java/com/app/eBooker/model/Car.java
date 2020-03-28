package com.app.eBooker.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Car {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String carType;
	@NotBlank
	private String brand;
	@NotBlank
	private String engineType;
	@NotBlank
	private String enginePower;
	@NotBlank
	private String yearOfProduction;
	@NotBlank
	private String seatsCount;
	@NotBlank
	private String tachometer;
	@NotBlank
	private String transmission;
	@NotBlank
	private String registeredNumber;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@NotNull
	private byte[] carPicture;
	@NotBlank
	private String status;
	
	@NotBlank
	private String location;
	
	private String comments;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public String getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(String enginePower) {
		this.enginePower = enginePower;
	}

	public String getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(String yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public String getSeatsCount() {
		return seatsCount;
	}

	public void setSeatsCount(String seatsCount) {
		this.seatsCount = seatsCount;
	}

	public String getTachometer() {
		return tachometer;
	}

	public void setTachometer(String tachometer) {
		this.tachometer = tachometer;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getRegisteredNumber() {
		return registeredNumber;
	}

	public void setRegisteredNumber(String registeredNumber) {
		this.registeredNumber = registeredNumber;
	}

	public byte[] getCarPicture() {
		return carPicture;
	}

	public void setCarPicture(byte[] carPicture) {
		this.carPicture = carPicture;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
	
	
}
