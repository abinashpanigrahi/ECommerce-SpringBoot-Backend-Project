package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.models.Customer;

public interface CustomerService {
	public Customer addCustomer(Customer customer) throws CustomerException;
}
