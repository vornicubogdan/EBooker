
package com.app.eBooker.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.eBooker.model.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long>{

}
