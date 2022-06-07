package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.models.Customer;
import com.masai.models.Order;
import com.masai.models.OrderDTO;
import com.masai.models.OrderStatusValues;
import com.masai.repository.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao oDao;
	
	@Override
	public Order saveOrder(OrderDTO odto,String token) throws LoginException {
		
		Order newOrder= new Order();
		
		CustomerService cs= new CustomerServiceImpl();
		
		if(cs !=null) {
			Customer loggedInCustomer= cs.getLoggedInCustomerDetails(token);
			newOrder.setCustomer(loggedInCustomer);
			newOrder.setCardNumber(odto.getCardNumber());
			newOrder.setAddress(loggedInCustomer.getAddress().get(odto.getAddressType()));
			newOrder.setDate(LocalDate.now());
			newOrder.setOrderStatus(OrderStatusValues.SUCCESS);
			newOrder.setTotal(10000.00);
			return oDao.save(newOrder);
		}
		else {
			throw new LoginException("Invalid session token for customer"+"Kindly Login");
		}
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
	public Order updateOrderByOrder(Order order) throws OrderException {
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

//	@Override
//	public Customer getCustomerIdByToken(String token) throws CustomerNotFoundException {
//		CustomerService cs= new CustomerServiceImpl();
//		
//		//Customer loggedInCustomer= cs.getLoggedInCustomerDetails(token);
//		return cs.getLoggedInCustomerDetails(token);
//		
//	}

}
