package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.OrderException;
import com.masai.models.Customer;
import com.masai.models.Order;

public interface OrderService {
	public Order saveOrder(Order order);
	
	public Order getOrderByOrderId(Integer OrderId) throws OrderException;
	
	public List<Order> getAllOrders() throws OrderException;
	
	public Order deleteOrderByOrderId(Integer OrderId) throws OrderException;
	
	public Order updateOrderByOrderId(Order order) throws OrderException;
	
	public List<Order> getAllOrdersByDate(LocalDate date) throws OrderException;

	public Customer getCustomerByOrderid(Integer orderId) throws OrderException;
	
	//public Integer getCustomerIdByToken(String token) throws CustomerNotFoundException;
}
