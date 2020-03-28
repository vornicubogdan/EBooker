package com.app.eBooker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.eBooker.model.Reservation;
import com.app.eBooker.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;
	
	@Autowired
	public ReservationServiceImpl(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
	
	@Override
	public List<Reservation> listAll() {
		List<Reservation> reservations = new ArrayList<>();
		reservationRepository.findAll().forEach(reservations::add);
		return reservations;
	}

	@Override
	public Reservation getById(Long id) {
		return reservationRepository.findById(id).orElse(null);
	}

	@Override
	public Reservation addReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation updateReservation(Long id, Reservation reservationToUpdate) {
		return reservationRepository.findById(id).map(r -> {
			reservationToUpdate.setId(id);
			return reservationRepository.save(reservationToUpdate);
		}).orElse(null);
	} 

	@Override
	public void deleteReservation(Long id) {
		reservationRepository.deleteById(id);
	}

	
}
