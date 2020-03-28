package com.app.eBooker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.eBooker.model.Car;
import com.app.eBooker.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{
	
	private final CarRepository carRepository;
	
	@Autowired
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public List<Car> listAll() {
		List<Car> cars = new ArrayList<>();
		carRepository.findAll().forEach(cars::add);
		return cars;
	}

	@Override
	public Car getById(Long id) {
		return carRepository.findById(id).orElse(null);
	}

	@Override
	public Car addCar(Car car) {
		return carRepository.save(car);
	}

	@Override
	public Car updateCar(Long id, Car carToUpdate) {
		
		return carRepository.findById(id).map(c -> {
			carToUpdate.setId(id);
			if(carToUpdate.getCarPicture() == null) {
				carToUpdate.setCarPicture(c.getCarPicture());
			}
			return carRepository.save(carToUpdate);
		}).orElse(null);
		
	}

	@Override
	public void deleteCar(Long id) {
		carRepository.deleteById(id);
	}

	
}
