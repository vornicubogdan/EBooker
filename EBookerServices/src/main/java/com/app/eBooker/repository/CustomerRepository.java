package com.app.eBooker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.app.eBooker.model.Customer;

public interface CustomerRepository extends CrudRepository <Customer, Long> {
		public Customer findByUsername(String username);

}
