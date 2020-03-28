package com.app.eBooker.service;

import java.util.List;

import com.app.eBooker.model.Customer;

public interface CustomerService {

	List<Customer> listAll();
	
	Customer getById(Long id);
	
	public Customer addCustomer(Customer customer);
	
	public Customer updateCustomer(Long id, Customer customerToUpdate);
	
	public void deleteCustomer(Long id);
	
}
