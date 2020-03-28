package com.app.eBooker.converters;


import com.app.eBooker.exceptions.ModelProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonParser {

	public CarInfo jsonToCarInfo(String carInfoString) {
		ObjectMapper mapper= new ObjectMapper();
		try {
			CarInfo carInfo;
			carInfo = mapper.readValue(carInfoString, CarInfo.class);
			return carInfo;
		} catch (JsonProcessingException ex) {
			throw new ModelProcessingException("Could not process following car info details: " + carInfoString + ". Please try again!", ex);
		}
	}
}
