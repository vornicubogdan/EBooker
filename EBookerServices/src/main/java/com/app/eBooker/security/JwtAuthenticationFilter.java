package com.app.eBooker.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.eBooker.model.AuthResponseModel;
import com.app.eBooker.model.Customer;
import com.app.eBooker.model.LoginViewModel;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter	{
	
		private AuthenticationManager authenticationManager;
		
		public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
			this.authenticationManager = authenticationManager;
		}
		
		//trigger when we POST request to /login + pass the username password in body of request
		@Override
		public Authentication attemptAuthentication(HttpServletRequest request,
				HttpServletResponse response) throws AuthenticationException {

			// get credentials into a loginmodel
			LoginViewModel credentials = null;
			try {
				credentials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
			}catch(IOException e) {
				e.printStackTrace();
			}
			//create login token
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					credentials.getUsername(),
					credentials.getPassword(),
					new ArrayList<>());
					
			//Authenticate user
			Authentication auth = authenticationManager.authenticate(authenticationToken);
			return auth;
		}
		
		@Override
		protected void successfulAuthentication(HttpServletRequest request,
				HttpServletResponse response, FilterChain chain, Authentication authResult)
				throws IOException, ServletException {
			
			//take the principal
			CustomerPrincipal principal = (CustomerPrincipal) authResult.getPrincipal();
			
			//Create JWT Token
		
		 String token = JWT.create()
				 .withSubject(principal.getUsername())
				 .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
				 .sign(HMAC512(JwtProperties.SECRET.getBytes()));
		 
		 // Add token in response
		 response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
		 response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		 response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS, EAD");
		 response.addHeader("Access-Control-Expose-Headers", "Authorization");
		 //Add Customer Details to Body
		 AuthResponseModel authResponse  = buildAuthResponseModel(principal);
		 String customerJsonString = new Gson().toJson(authResponse);
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().print(customerJsonString); 
		 response.getWriter().flush();
		}
		
		private AuthResponseModel buildAuthResponseModel(CustomerPrincipal principal) {
			AuthResponseModel authResponse = new AuthResponseModel();
			authResponse.setCity(principal.getCity());
			authResponse.setEmail(principal.getEmail());
			authResponse.setFirstName(principal.getFirstName());
			authResponse.setLastName(principal.getLastName());
			authResponse.setPhone(principal.getPhone());
			authResponse.setPermissions(principal.getPermissions());
			authResponse.setRoles(principal.getRoles());
			authResponse.setUsername(principal.getUsername());
			return authResponse;
		}
		
		

}
