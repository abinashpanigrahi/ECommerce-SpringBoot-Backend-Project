package com.masai.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Customer;
import com.masai.models.Order;
import com.masai.repository.OrderDao;
import com.masai.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderDao oDao;
	
	@Autowired
	private OrderService oService;
	
	@PostMapping("/orders")
	public ResponseEntity<Order> addTheNewOrder(@Valid @RequestBody Order order){
		
		Order savedorder = oService.saveOrder(order);
		return new ResponseEntity<Order>(savedorder,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/orders")
	public List<Order> getAllOrders(){
		
		
		List<Order> listOfAllOrders = oService.getAllOrders();
		return listOfAllOrders;
		
	}
	
	@GetMapping("/orders/{orderId}")
	public Order getOrdersByOrderId(@PathVariable("orderId") Integer orderId) {
		
		return oService.getOrderByOrderId(orderId);
		
	}
	
	@DeleteMapping("/orders/{orderId}")
	public Order deleteOrderByOrderId(@PathVariable("orderId") Integer orderId){
		
		return oService.deleteOrderByOrderId(orderId);
	}
	
	@PutMapping("/order")
	public ResponseEntity<Order> updateOrderByOrderId(@Valid @RequestBody Order order){
		
		Order updatedOrder= oService.updateOrderByOrderId(order);
		
		return new ResponseEntity<Order>(updatedOrder,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/orders/by/date")
	public List<Order> getOrdersByDate(@RequestParam("date") String date){
		
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate ld=LocalDate.parse(date,dtf);
		return oService.getAllOrdersByDate(ld);
	}
	
	@GetMapping("/customer/{orderId}")
	public Customer getCustomerDetailsByOrderId(@PathVariable("orderId") Integer orderId) {
		return oService.getCustomerByOrderid(orderId);
	}
}
