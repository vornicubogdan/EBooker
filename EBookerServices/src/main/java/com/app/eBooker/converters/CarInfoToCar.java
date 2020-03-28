package com.app.eBooker.converters;


import java.io.IOException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

import com.app.eBooker.exceptions.FileStorageException;
import com.app.eBooker.model.Car;


public class CarInfoToCar implements Converter<CarInfo, Car> {

	private MultipartFile image;
	
	@Override
	public Car convert(CarInfo source) {
		try {
			Car car = new Car();
			car.setId(source.getId());
			car.setCarType(source.getCarType());
			car.setBrand(source.getBrand());
			car.setEngineType(source.getEngineType());
			car.setEnginePower(source.getEnginePower());
			car.setTachometer(source.getTachometer());
			car.setTransmission(source.getTransmission());
			car.setYearOfProduction(source.getYearOfProduction());
			car.setStatus(source.getStatus());
			car.setSeatsCount(source.getSeatsCount());
			car.setRegisteredNumber(source.getRegisteredNumber());
			car.setLocation(source.getLocation());
			car.setComments(source.getComments());
			if(!(image == null)) {
				car.setCarPicture(image.getBytes());
			}
			return car;
		}catch(IOException ex) {
			throw new FileStorageException("Could not upload car image " + image.getOriginalFilename() + ". Please try again!", ex);
		}
	}


	public Car mergeIntoCar(String carInfoString, MultipartFile image) {
		JsonParser parser = new JsonParser();
		CarInfo carInfo = parser.jsonToCarInfo(carInfoString);
		this.setImage(image);
		return this.convert(carInfo);
	}
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	
}
