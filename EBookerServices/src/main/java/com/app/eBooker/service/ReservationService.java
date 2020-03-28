package com.app.eBooker.service;

import java.util.List;

import com.app.eBooker.model.Reservation;


public interface ReservationService {

	List<Reservation> listAll();
	
	Reservation getById(Long id);
	
	public Reservation addReservation(Reservation reservation);
	
	public Reservation updateReservation(Long id, Reservation reservationToUpdate);
	
	public void deleteReservation(Long id);
}
