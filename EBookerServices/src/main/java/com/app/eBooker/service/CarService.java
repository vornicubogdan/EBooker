package com.app.eBooker.service;

import java.util.List;

import com.app.eBooker.model.Car;

public interface CarService {

	List<Car> listAll();
	
	Car getById(Long id);
	
	public Car addCar(Car car);
	
	public Car updateCar(Long id, Car carToUpdate);
	
	public void deleteCar(Long id);
}
