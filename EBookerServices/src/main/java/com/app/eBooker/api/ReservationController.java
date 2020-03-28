package com.app.eBooker.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.eBooker.model.Reservation;
import com.app.eBooker.service.ReservationService;

@RequestMapping("eBooker/reservation")
@RestController
public class ReservationController {

	private final ReservationService reservationService;
	
	@Autowired
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@GetMapping
	public List<Reservation> getAllReservations() {
		return reservationService.listAll();
	}
	
	@GetMapping(path = "{id}")
	public Reservation getReservationById(@PathVariable("id") Long id) {
			return reservationService.getById(id);
	}
	
	@PostMapping
	public Reservation addReservation(@Valid @NotNull @RequestBody Reservation reservation) {
		return reservationService.addReservation(reservation);
	}
	
	@PutMapping(path = "{id}")
	public Reservation updateReservationById(@PathVariable("id") Long id, @Valid @NotNull @RequestBody Reservation reservationToUpdate) {
		return reservationService.updateReservation(id, reservationToUpdate);
	}
	
	@DeleteMapping(path = "{id}")
	public void deleteReservationById(@PathVariable("id") Long id) {
		reservationService.deleteReservation(id);
	}
}
