package com.monocept.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.monocept.security.filter.AuthenticationFilter;
import com.monocept.security.filter.ExceptionHandlerFilter;
import com.monocept.security.filter.JWTAuthorizationFilter;
import com.monocept.security.manager.CustomAuthenticationManager;

//import lombok.AllArgsConstructor;

import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	CustomAuthenticationManager authenticationManager;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticateFilter=new AuthenticationFilter(authenticationManager);
        authenticateFilter.setFilterProcessesUrl("/login");
    	
    	http 
    		.cors().and()
            .csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST,SecurityConstants.REGISTER_USER_PATH).permitAll()
            .requestMatchers(HttpMethod.POST,"/employee/save").permitAll()
            .requestMatchers("/api/v1/employee/**").hasAuthority("admin")
            .requestMatchers("/api/v1/agent/**").hasAnyAuthority("admin","employee","agent")
            .requestMatchers("/api/v1/customer/get/**").permitAll()
            .requestMatchers("/api/v1/customer/getcustomer/**").permitAll()
            .requestMatchers("/api/v1/customer/getall").hasAnyAuthority("admin","employee","agent")
            .requestMatchers("/api/v1/customer/**").hasAnyAuthority("admin","employee","agent")
            .requestMatchers("/api/v1/customer/**").hasAnyAuthority("admin","employee")
            .requestMatchers("/api/v1/insuranceplan/getall").permitAll()
            .requestMatchers("/api/v1/insuranceplan/**").permitAll()
            .requestMatchers("/api/v1/insuranceapp/**").permitAll()
            .requestMatchers("/api/v1/insuranceapp/payment/**").permitAll()
            .requestMatchers("policy/save").hasAuthority("admin")
            .requestMatchers("file/approve/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authenticateFilter)
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }  
    
      
}