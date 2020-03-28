package com.app.eBooker.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.eBooker.converters.CarInfoToCar;
import com.app.eBooker.model.Car;
import com.app.eBooker.service.CarService;


@RequestMapping("eBooker/car")
@RestController
public class CarController {
	
	private final CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping
	public List<Car> getAllCars(){
		List<Car> cars = new ArrayList<>();
		carService.listAll().forEach(cars::add);
		return cars;
	}
	
	@GetMapping(path = "{id}")
	public Car getCarById(@PathVariable("id") Long id) {
		return carService.getById(id);
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Car addCar(@Valid @NonNull @RequestParam("carInfo") String carInfoString, @RequestParam(value = "image", required = true) MultipartFile image) {
		CarInfoToCar toCarConverter = new CarInfoToCar();
		Car car = toCarConverter.mergeIntoCar(carInfoString, image);
		return carService.addCar(car);
	}
	
	@PutMapping(path = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Car updateCarById(@PathVariable("id") Long id, @Valid @NonNull @RequestParam("carInfo") String carInfoString, @RequestParam(value = "image", required = false) MultipartFile image) {
		CarInfoToCar toCarConverter = new CarInfoToCar();
		Car carToUpdate = toCarConverter.mergeIntoCar(carInfoString, image);
		return carService.updateCar(id, carToUpdate);
	}
	
	@DeleteMapping(path = "{id}")
	public void deleteCarById(@PathVariable("id") Long id) {
		carService.deleteCar(id);
	}
}
