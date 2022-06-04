package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.models.Customer;
import com.masai.repository.CustomerDao;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao cDao;
	
	@Override
	public Customer addCustomer(Customer customer) {
				
		customer.setCreatedOn(LocalDateTime.now());

		Optional<Customer> existing = cDao.findByMobileNo(customer.getMobileNo());
		
		if(existing.isPresent())
			throw new CustomerException("Customer already exists. Please try to login with your mobile no");
		
		cDao.save(customer);
		
		return customer;
	}

}
