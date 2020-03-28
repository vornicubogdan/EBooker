package com.app.eBooker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.eBooker.model.Customer;
import com.app.eBooker.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder =passwordEncoder;
    }

	@Override
	public List<Customer> listAll() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		return customers;
	}

	@Override
	public Customer getById(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Customer addCustomer(Customer customer) {
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Long id, Customer customerToUpdate) {
		return customerRepository.findById(id).map(c -> {
			customerToUpdate.setId(id);
			return customerRepository.save(customerToUpdate);
		}).orElse(null);
	}

	
	
	
	
	
}
