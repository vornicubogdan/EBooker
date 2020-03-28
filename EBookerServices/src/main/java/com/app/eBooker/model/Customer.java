package com.app.eBooker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String address;
	@NotBlank
	private String city;
	@NotBlank
	private String email;
	@NotBlank
	private String phone;
	@NotBlank
	private String identityCardNumber;
	@NotBlank
	private String active;
	
	private String roles;

	private String permissions;
	 
	 public Customer(String username, String password, String roles, String permissions){
	        this.username = username;
	        this.password = password;
	        this.roles = roles;
	        this.permissions = permissions;
	        this.active = "true";
	    }

	    protected Customer(){}
	
	/*
	 * public Customer(@JsonProperty("id") long id, @JsonProperty("firstName")
	 * String firstName,
	 * 
	 * @JsonProperty("lastName") String lastName, @JsonProperty("username") String
	 * username,
	 * 
	 * @JsonProperty("password") String password, @JsonProperty("address") String
	 * address,
	 * 
	 * @JsonProperty("city") String city, @JsonProperty("email") String email,
	 * 
	 * @JsonProperty("phone") String phone, @JsonProperty("identityCardNumber")
	 * String identityCardNumber) {
	 * 
	 * this.id = id; this.firstName = firstName; this.lastName = lastName;
	 * this.username = username; this.password = password; this.city = city;
	 * this.address = address; this.email = email; this.phone = phone;
	 * this.identityCardNumber = identityCardNumber; }
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}
	

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
	
	
	
	
	
}
