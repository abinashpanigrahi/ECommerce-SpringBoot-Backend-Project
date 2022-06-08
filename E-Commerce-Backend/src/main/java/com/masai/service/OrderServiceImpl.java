package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.models.CartDTO;
import com.masai.models.CartItem;
import com.masai.models.Customer;
import com.masai.models.Order;
import com.masai.models.OrderDTO;
import com.masai.models.OrderStatusValues;
import com.masai.models.Product;
import com.masai.repository.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao oDao;
	
	@Autowired
	private CustomerService cs;
	
	@Autowired
	private CartServiceImpl cartservicei;
	
	
	@Override
	public Order saveOrder(OrderDTO odto,String token) throws LoginException, OrderException {
		
		Order newOrder= new Order();
		
		if(cs !=null) {
			Customer loggedInCustomer= cs.getLoggedInCustomerDetails(token);
			newOrder.setCustomer(loggedInCustomer);
			String usersCardNumber= loggedInCustomer.getCreditCard().getCardNumber();
			String userGivenCardNumber= odto.getCardNumber().getCardNumber();
			List<CartItem> productsInCart= loggedInCustomer.getCustomerCart().getCartItems();
			List<CartItem> productsInOrder = new ArrayList<>(productsInCart);
			
			newOrder.setOrdercartItems(productsInOrder);
			newOrder.setTotal(loggedInCustomer.getCustomerCart().getCartTotal());
			
			//System.out.println(newOrder);
			
			if(productsInCart.size()!=0) {
				if(usersCardNumber.equals(userGivenCardNumber)) {
					
					newOrder.setCardNumber(odto.getCardNumber().getCardNumber());
					newOrder.setAddress(loggedInCustomer.getAddress().get(odto.getAddressType()));
					newOrder.setDate(LocalDate.now());
					newOrder.setOrderStatus(OrderStatusValues.SUCCESS);
					List<CartItem> cartItemsList= loggedInCustomer.getCustomerCart().getCartItems();
					
					for(CartItem cartItem : cartItemsList ) {
						Integer remainingQuantity = cartItem.getCartProduct().getQuantity()-cartItem.getCartItemQuantity();
						cartItem.getCartProduct().setQuantity(remainingQuantity);
						CartDTO cdto = new CartDTO();
						cdto.setProductId(cartItem.getCartProduct().getProductId());
						cartservicei.removeProductFromCart(cdto, token);
					}
			
					//System.out.println(newOrder);
					return oDao.save(newOrder);
				}
				else {
					System.out.println("Not same");
					newOrder.setCardNumber(null);
					newOrder.setAddress(loggedInCustomer.getAddress().get(odto.getAddressType()));
					newOrder.setDate(LocalDate.now());
					newOrder.setOrderStatus(OrderStatusValues.PENDING);
					return oDao.save(newOrder);
					
				}
			}
			else {
				throw new OrderException("No products in Cart");
			}
			
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
	public Order cancelOrderByOrderId(Integer OrderId) throws OrderException {
		Order order= oDao.findById(OrderId).orElseThrow(()->new OrderException("No order exists with given OrderId "+ OrderId));
		oDao.deleteById(OrderId);
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
