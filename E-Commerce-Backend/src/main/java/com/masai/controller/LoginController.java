package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Customer;
import com.masai.service.CustomerService;

@RestController
public class LoginController {
	
	@Autowired
	private CustomerService cService;

	
	@PostMapping(value = "/register", consumes = "application/json")
	public ResponseEntity<Customer> registerAccountHandler(@Valid @RequestBody Customer customer) {
		System.out.println("Inside post mapping");
		return new ResponseEntity<>(cService.addCustomer(customer), HttpStatus.CREATED);
	}
	
}
