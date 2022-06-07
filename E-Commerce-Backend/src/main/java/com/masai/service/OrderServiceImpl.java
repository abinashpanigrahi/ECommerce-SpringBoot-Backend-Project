package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.OrderException;
import com.masai.models.Customer;
import com.masai.models.Order;
import com.masai.repository.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao oDao;
	
	@Override
	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		return oDao.save(order);
	}

	@Override
	public Order getOrderByOrderId(Integer OrderId) throws OrderException {
		return oDao.findById(OrderId).orElseThrow(()-> new OrderException("No order exists with given OrderId "+ OrderId));
		
	}

	@Override
	public List<Order> getAllOrders() throws OrderException {
		// TODO Auto-generated method stub
		List<Order> orders = oDao.findAll();
		if(orders.size()>0)
			return orders;
		else
			throw new OrderException("No Orders exists on your account");
	}

	@Override
	public Order deleteOrderByOrderId(Integer OrderId) throws OrderException {
		Order order= oDao.findById(OrderId).orElseThrow(()->new OrderException("No order exists with given OrderId "+ OrderId));
		oDao.delete(order);
		return order;
	}

	@Override
	public Order updateOrderByOrderId(Order order) throws OrderException {
		Order existingOrder= oDao.findById(order.getOrderId()).orElseThrow(()->new OrderException("No order exists with given OrderId "+ order.getOrderId()));
		return oDao.save(order);
	}

	@Override
	public List<Order> getAllOrdersByDate(LocalDate date) throws OrderException {
		List<Order> listOfOrdersOntheDay= oDao.findByDate(date);
		return listOfOrdersOntheDay;
	}

	@Override
	public Customer getCustomerByOrderid(Integer orderId) throws OrderException {
		Optional<Order> opt= oDao.findById(orderId);
		if(opt.isPresent()) {
			Order existingorder= opt.get();
			
			return oDao.getCustomerByOrderid(existingorder.getCustomer().getCustomerId());
		}
		else
			throw new OrderException("No Order exists with orderId "+orderId);
	}

}
