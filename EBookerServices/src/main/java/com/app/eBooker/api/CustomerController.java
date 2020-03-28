package com.app.eBooker.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.eBooker.model.Customer;
import com.app.eBooker.service.CustomerService;

@RequestMapping("eBooker/customer")
@RestController
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping
	public void addCustomer(@Valid @NonNull @RequestBody Customer customer) {
		customerService.addCustomer(customer);
	}
	
	@GetMapping
	public List<Customer> getAllCustomers(){
		return customerService.listAll();
	}
	
	@GetMapping(path ="{id}")
	public Customer getCustomerById(@PathVariable("id") Long id) {
		return customerService.getById(id);
	}
	
	@DeleteMapping(path ="{id}")
	public void deleteCustomerById(@PathVariable("id") Long id) {
		 customerService.deleteCustomer(id);
	}
	
	@PutMapping(path ="{id}")
	public Customer updateCustomerById(@PathVariable("id") Long id, @Valid @NonNull @RequestBody Customer customerToUpdate) {
		return customerService.updateCustomer(id, customerToUpdate);
	}
	
}
