package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.LoginException;
import com.masai.exception.SellerNotFoundException;
import com.masai.models.Customer;
import com.masai.models.CustomerDTO;
import com.masai.models.Seller;
import com.masai.models.SellerDTO;
import com.masai.models.SessionDTO;
import com.masai.models.UserSession;
import com.masai.repository.CustomerDao;
import com.masai.repository.SellerDao;
import com.masai.repository.SessionDao;

@Service
public class LoginLogoutServiceImpl implements LoginLogoutService{

	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SellerDao sellerDao;

 
	
	// Method to login a customer

	@Override
	public UserSession loginCustomer(CustomerDTO loginCustomer) {
		
		Optional<Customer> res = customerDao.findByMobileNo(loginCustomer.getMobileId());
		
		if(res.isEmpty())
			throw new CustomerNotFoundException("Customer record does not exist with given mobile number");
		
		Customer existingCustomer = res.get();
		
		Optional<UserSession> opt = sessionDao.findByUserId(existingCustomer.getCustomerId());
		
		if(opt.isPresent()) {
			
			UserSession user = opt.get();
			
			if(user.getSessionEndTime().isBefore(LocalDateTime.now())) {
				sessionDao.delete(user);	
			}
			else
				throw new LoginException("User already logged in");
			
		}
		
		
		if(existingCustomer.getPassword().equals(loginCustomer.getPassword())) {
		
			UserSession newSession = new UserSession();
			
			newSession.setUserId(existingCustomer.getCustomerId());
			newSession.setUserType("customer");
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));
			
			UUID uuid = UUID.randomUUID();
			String token = "customer_" + uuid.toString().split("-")[0];
			
			newSession.setToken(token);
			
			return sessionDao.save(newSession);
		}
		else {
			throw new LoginException("Password Incorrect. Try again.");
		}
	}

	
	// Method to logout a customer
	
	@Override
	public SessionDTO logoutCustomer(SessionDTO sessionToken) {
		
		String token = sessionToken.getToken();
		
		checkTokenStatus(token);
		
		Optional<UserSession> opt = sessionDao.findByToken(token);
		
		if(!opt.isPresent())
			throw new LoginException("User not logged in. Invalid session token. Login Again.");
		
		UserSession session = opt.get();
		
		sessionDao.delete(session);
		
		sessionToken.setMessage("Logged out sucessfully.");
		
		return sessionToken;
	}
	
	
	
	// Method to check status of session token
	
	
	@Override
	public void checkTokenStatus(String token) {
		
		Optional<UserSession> opt = sessionDao.findByToken(token);
		
		if(opt.isPresent()) {
			UserSession session = opt.get();
			LocalDateTime endTime = session.getSessionEndTime();
			boolean flag = false;
			if(endTime.isBefore(LocalDateTime.now())) {
				sessionDao.delete(session);
				flag = true;
			}
			
			deleteExpiredTokens();
			if(flag)
				throw new LoginException("Session expired. Login Again");
		}
		else {
			throw new LoginException("User not logged in. Invalid session token. Please login first.");
		}
		
	}

	
	// Method to login a valid seller and generate a seller token
	
	@Override
	public UserSession loginSeller(SellerDTO seller) {
		
		Optional<Seller> res = sellerDao.findByMobile(seller.getMobile());
		
		if(res.isEmpty())
			throw new SellerNotFoundException("Seller record does not exist with given mobile number");
		
		Seller existingSeller = res.get();
		
		Optional<UserSession> opt = sessionDao.findByUserId(existingSeller.getSellerId());
		
		if(opt.isPresent()) {
			
			UserSession user = opt.get();
			
			if(user.getSessionEndTime().isBefore(LocalDateTime.now())) {
				sessionDao.delete(user);	
			}
			else
				throw new LoginException("User already logged in");
			
		}
		
		
		if(existingSeller.getPassword().equals(seller.getPassword())) {
		
			UserSession newSession = new UserSession();
			
			newSession.setUserId(existingSeller.getSellerId());
			newSession.setUserType("seller");
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));
			
			UUID uuid = UUID.randomUUID();
			String token = "seller_" + uuid.toString().split("-")[0];
			
			newSession.setToken(token);
			
			return sessionDao.save(newSession);
		}
		else {
			throw new LoginException("Password Incorrect. Try again.");
		}
	}

	
	// Method to logout a seller and delete his session token
	
	@Override
	public SessionDTO logoutSeller(SessionDTO session) {
		
		String token = session.getToken();
		
		checkTokenStatus(token);
		
		Optional<UserSession> opt = sessionDao.findByToken(token);
		
		if(!opt.isPresent())
			throw new LoginException("User not logged in. Invalid session token. Login Again.");
		
		UserSession user = opt.get();
		
		sessionDao.delete(user);
		
		session.setMessage("Logged out sucessfully.");
		
		return session;
	}
	
	
	// Method to delete expired tokens
	
	@Override
	public void deleteExpiredTokens() {
		
		System.out.println("Inside delete tokens");
		
		List<UserSession> users = sessionDao.findAll();
		
		System.out.println(users);
		
		if(users.size() > 0) {
			for(UserSession user:users) {
				System.out.println(user.getUserId());
				LocalDateTime endTime = user.getSessionEndTime();
				if(endTime.isBefore(LocalDateTime.now())) {
					System.out.println(user.getUserId());
					sessionDao.delete(user);
				}
			}
		}
	}
	
}
