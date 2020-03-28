package com.app.eBooker.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.eBooker.model.Customer;
import com.app.eBooker.repository.CustomerRepository;

@Service
public class CustomerPrincipalDetailsService implements UserDetailsService{

	private CustomerRepository customerRepository;
	
	public CustomerPrincipalDetailsService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = this.customerRepository.findByUsername(username);
		CustomerPrincipal customerPricipal = new CustomerPrincipal(customer);
		
		return customerPricipal;
	}

}
