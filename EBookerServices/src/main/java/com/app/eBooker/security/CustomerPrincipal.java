package com.app.eBooker.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.eBooker.model.Customer;

public class CustomerPrincipal implements UserDetails{

	private Customer customer;
	
	public CustomerPrincipal(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.customer.getPassword();
	}

	@Override
	public String getUsername() {
		return this.customer.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getFirstName() {
		return customer.getFirstName();
	}
	
	public String getLastName() {
		return customer.getLastName();
	}
	
	public String getCity() {
		return customer.getCity();
	}

	public String getEmail() {
		return customer.getEmail();
	}
	
	public String getPhone() {
		return customer.getPhone();
	}
	
	public String getRoles() {
		return customer.getRoles();
	}
	
	public String getPermissions() {
		return customer.getPermissions();
	}
}
