package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.LoginException;
import com.masai.models.Address;
import com.masai.models.Customer;
import com.masai.models.CustomerDTO;
import com.masai.models.CustomerUpdateDTO;
import com.masai.models.SessionDTO;
import com.masai.repository.CustomerDao;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private LoginLogoutService loginService;
	
	
	// Method to add a new customer
	
	@Override
	public Customer addCustomer(Customer customer) {
				
		customer.setCreatedOn(LocalDateTime.now());

		Optional<Customer> existing = customerDao.findByMobileNo(customer.getMobileNo());
		
		if(existing.isPresent())
			throw new CustomerException("Customer already exists. Please try to login with your mobile no");
		
		customerDao.save(customer);
		
		return customer;
	}

	
	
	// Method to get a customer by mobile number
	
	@Override
	public Customer getCustomerByMobileNo(String mobileNo, String token){
		
		if(token.contains("customer") == false) {
			throw new LoginException("Invalid session token for customer");
		}
		
		loginService.checkTokenStatus(token);
		
		
		Optional<Customer> opt = customerDao.findByMobileNo(mobileNo);
		
		if(opt.isEmpty())
			throw new CustomerNotFoundException("Customer not registered with given mobile number or email-id");
		
		return opt.get();
	}
	
	

	
	// Method to get all customers - only selller or admin can get all customers - check validity of seller token

	@Override
	public List<Customer> getAllCustomers(String token) throws CustomerNotFoundException {
		
		// update to seller
		
		if(token.contains("customer") == false) {
			throw new LoginException("Invalid session token for customer");
		}
		
		loginService.checkTokenStatus(token);
		
		List<Customer> customers = customerDao.findAll();
		
		if(customers.size() == 0)
			throw new CustomerNotFoundException("No record exists");
		
		return customers;
	}


	// Method to update entire customer details - either mobile number or mobile number should be correct
	
	@Override
	public Customer updateCustomer(CustomerUpdateDTO customer, String token) throws CustomerNotFoundException {
		
		System.out.println(customer);
		
		if(token.contains("customer") == false) {
			throw new LoginException("Invalid session token for customer");
		}
		
		loginService.checkTokenStatus(token);
		
		Optional<Customer> opt = customerDao.findByMobileNo(customer.getMobileNo());
		
		Optional<Customer> res = customerDao.findByEmailId(customer.getEmailId());
		
		if(opt.isEmpty() && res.isEmpty())
			throw new CustomerNotFoundException("Customer does not exist with given mobile no or email-id");
		
		Customer existingCustomer = opt.get();
		
		if(customer.getFirstName() != null) {
			existingCustomer.setFirstName(customer.getFirstName());
		}
		
		if(customer.getLastName() != null) {
			existingCustomer.setLastName(customer.getLastName());
		}
		
		if(customer.getEmailId() != null) {
			existingCustomer.setEmailId(customer.getEmailId());
		}
		
		if(customer.getMobileNo() != null) {
			existingCustomer.setMobileNo(customer.getMobileNo());
		}
		
		if(customer.getPassword() != null) {
			existingCustomer.setPassword(customer.getPassword());
		}
		
		if(customer.getAddress() != null) {			
			for(Map.Entry<String, Address> values : customer.getAddress().entrySet()) {
				existingCustomer.getAddress().put(values.getKey(), values.getValue());
			}
		}
		
		System.out.println(existingCustomer);
		
		customerDao.save(existingCustomer);
		
		return existingCustomer;
	}

	
	// Method to update customer mobile number - customer should send valid email-id

	@Override
	public Customer updateCustomerMobileNoOrEmailId(CustomerUpdateDTO customerUpdateDTO, String token) throws CustomerNotFoundException {
		
		if(token.contains("customer") == false) {
			throw new LoginException("Invalid session token for customer");
		}
		
		loginService.checkTokenStatus(token);
		
		Optional<Customer> opt = customerDao.findByEmailId(customerUpdateDTO.getEmailId());
		
		if(opt.isEmpty())
			throw new CustomerNotFoundException("Customer does not exist with given email-id");
		
		Customer existingCustomer = opt.get();
		
		existingCustomer.setMobileNo(customerUpdateDTO.getMobileNo());
		
		customerDao.save(existingCustomer);
		
		return existingCustomer;
	}

	// Method to update password 
	
	@Override
	public Customer updateCustomerPassword(CustomerDTO customerDTO, String token) {
			
		if(token.contains("customer") == false) {
			throw new LoginException("Invalid session token for customer");
		}
			
		loginService.checkTokenStatus(token);
			
		Optional<Customer> opt = customerDao.findByMobileNoOrEmailId(customerDTO.getMobileId(), customerDTO.getEmailId());
			
		if(opt.isEmpty())
			throw new CustomerNotFoundException("Customer not registered with given mobile number");
			
		Customer existingCustomer = opt.get();
			
		existingCustomer.setPassword(customerDTO.getPassword());
			
		return customerDao.save(existingCustomer);
	}

	
	
	// Method to delete a customer by mobile id
	
	@Override
	public SessionDTO deleteCustomer(CustomerDTO customerDTO, String token) throws CustomerNotFoundException {
		
		if(token.contains("customer") == false) {
			throw new LoginException("Invalid session token for customer");
		}
		
		loginService.checkTokenStatus(token);
		
		Optional<Customer> opt = customerDao.findByMobileNo(customerDTO.getMobileId());
		
		if(opt.isEmpty())
			throw new CustomerNotFoundException("Customer Not exist with given mobile no");
		
		Customer deletedCustomer = opt.get();
		
		customerDao.delete(deletedCustomer);
		
		SessionDTO session = new SessionDTO();
		
		session.setMessage("Account Deleted and Logged out");

		session.setToken(token);
		
		return loginService.logoutCustomer(session);
		
	}
	
	
	
	

}
