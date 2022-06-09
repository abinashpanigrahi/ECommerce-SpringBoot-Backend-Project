package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.models.Customer;
import com.masai.models.Order;
import com.masai.models.OrderDTO;

public interface OrderService {
	public Order saveOrder(OrderDTO odto,String token) throws LoginException, OrderException;
	
	public Order getOrderByOrderId(Integer OrderId) throws OrderException;
	
	public List<Order> getAllOrders() throws OrderException;
	
	public Order cancelOrderByOrderId(Integer OrderId,String token) throws OrderException;
	
	public Order updateOrderByOrder(OrderDTO order,Integer OrderId,String token) throws OrderException,LoginException;
	
	public List<Order> getAllOrdersByDate(LocalDate date) throws OrderException;

	public Customer getCustomerByOrderid(Integer orderId) throws OrderException;
	
	//public Customer getCustomerIdByToken(String token) throws CustomerNotFoundException;
	

	
}
