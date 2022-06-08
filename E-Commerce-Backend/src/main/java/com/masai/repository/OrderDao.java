package com.masai.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.OrderException;
import com.masai.models.Customer;
import com.masai.models.Order;
import com.masai.models.Product;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
	public List<Order> findByDate(LocalDate date);
	
//	@Query("select new com.masai.model.Order")
//	public List<Order> getListOfOrdersByCustomerid(Integer customerId);
	
	@Query("select c from Customer c where c.customerId = customerId")
	public Customer getCustomerByOrderid(@Param("customerId") Integer customerId);
	
//	public List<Product> getListOfProductsByOrderId(Integer OrderId);
	
//	@Query("update Order o set o.orderStatus =OrderStatusValues.CANCELLED WHERE o.OrderId=OrderId ")
//	public Order CancelOrderByOrderId(@Param("OrderId") Integer OrderId);
	
}
