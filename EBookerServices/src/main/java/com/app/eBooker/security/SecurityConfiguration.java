package com.app.eBooker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.app.eBooker.repository.CustomerRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private CustomerPrincipalDetailsService customerPrincipalDetailsService;
	private CustomerRepository customerRepository;
	
	
	@Autowired
	public SecurityConfiguration(CustomerPrincipalDetailsService customerPrincipalDetailsService, CustomerRepository customerRepository) {
		this.customerPrincipalDetailsService = customerPrincipalDetailsService;
		this.customerRepository = customerRepository;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authenticationProvider());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//remove csrf and state in session because in jwt we do not need them
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		//add jwt filters
		.addFilter(new JwtAuthenticationFilter(authenticationManager()))
		.addFilter(new JwtAuthorizationFilter(authenticationManager(), this.customerRepository))
		.authorizeRequests()
		//configure access rules
		.antMatchers("/login").permitAll()
		.antMatchers("/eBooker/**").permitAll()
		.antMatchers("/admin/**").hasRole("ADMIN");
	}
	
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.customerPrincipalDetailsService);
		
		return daoAuthenticationProvider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new  BCryptPasswordEncoder();
	}
}
