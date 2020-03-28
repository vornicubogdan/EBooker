package com.app.eBooker.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.app.eBooker.model.Customer;
import com.app.eBooker.repository.CustomerRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

	public CustomerRepository customerRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, CustomerRepository customerRepository) {
		super(authenticationManager);
		this.customerRepository = customerRepository;
	}
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
		//Read Authorization Header, where the JWT token should be
		String header = request.getHeader(JwtProperties.HEADER_STRING);
		
		//if header does not contain BEARER or is null delegate to Spring impl and exit
		if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		//if header is present, try to get customer principal from db and perform authorization
		Authentication authentication = getUsernamePasswordAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//Continue filter execution
		chain.doFilter(request, response);
	}
	
	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader(JwtProperties.HEADER_STRING);
		if(token != null) {
			//parse the token and validate it
			String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
					.build()
					.verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
					.getSubject();
			
			//Search in the DB if we find the user by token subject (username)
			// If so, then get the user details and create spring auth token using username, pass, authorities/roles
			if(username != null) {
				Customer customer = customerRepository.findByUsername(username);
				CustomerPrincipal principal = new CustomerPrincipal(customer);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, principal.getAuthorities());
				return null;
			}
		}
		
		return null;
	}

}
