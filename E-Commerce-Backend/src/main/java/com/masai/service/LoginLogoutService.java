package com.masai.service;

import com.masai.models.CustomerDTO;
import com.masai.models.SessionDTO;
import com.masai.models.UserSession;


public interface LoginLogoutService {
	
	public UserSession loginCustomer(CustomerDTO customer);
	
	public SessionDTO logoutCustomer(SessionDTO session);
	
	public void checkTokenStatus(String token);
	
}
