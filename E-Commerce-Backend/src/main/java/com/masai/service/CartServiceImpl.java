package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CartItemNotFound;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.LoginException;
import com.masai.models.Cart;
import com.masai.models.Customer;
import com.masai.models.Product;
import com.masai.models.UserSession;
import com.masai.repository.CartDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.SessionDao;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private LoginLogoutService loginService;

	@Override
	public Cart addProductToCart(Product product, String token,Integer quantity) {
		
		if(token.contains("customer") == false) {
		throw new LoginException("Invalid session token for customer");
	}
	
	loginService.checkTokenStatus(token);
	
	UserSession user = sessionDao.findByToken(token).get();
	
	Optional<Customer> opt = customerDao.findById(user.getUserId());
	
	if(opt.isEmpty())
		throw new CustomerNotFoundException("Customer does not exist");
	
	Customer existingCustomer = opt.get();
		
	
		
		Integer cartId = existingCustomer.getCustomerCart().getCartId();
		
		List<Product> cartProducts = existingCustomer.getCustomerCart().getProducts();
		
		for(Product existing : cartProducts) {
			if(product.getProductId() == existing.getProductId()) {
				product.setReqQuantity(quantity);
				cartDao.save(existingCustomer.getCustomerCart());
				return  existingCustomer.getCustomerCart();
				
			}
		}
		cartProducts.add(product);
		cartDao.save(existingCustomer.getCustomerCart());
		return  existingCustomer.getCustomerCart();
		
		
	}
	
	

	@Override
	public List<Product> getCartProduct(String token) {
		if(token.contains("customer") == false) {
			throw new LoginException("Invalid session token for customer");
		}
		
		loginService.checkTokenStatus(token);
		
		UserSession user = sessionDao.findByToken(token).get();
		
		Optional<Customer> opt = customerDao.findById(user.getUserId());
		
		if(opt.isEmpty())
			throw new CustomerNotFoundException("Customer does not exist");
		
		Customer existingCustomer = opt.get();
		Integer cartId = existingCustomer.getCustomerCart().getCartId();
		Optional<Cart> optCart= cartDao.findById(cartId);
		
		if(optCart.isEmpty()) {
			throw new CartItemNotFound("cart Not found by Id");
		}
		return optCart.get().getProducts();
		
//		return cart.getProducts();
	}

	
	
	@Override
	public Cart removeProductFromCart(Product product, String token) {
		if(token.contains("customer") == false) {
			throw new LoginException("Invalid session token for customer");
		}
		
		loginService.checkTokenStatus(token);
		
		UserSession user = sessionDao.findByToken(token).get();
		
		Optional<Customer> opt = customerDao.findById(user.getUserId());
		
		if(opt.isEmpty())
			throw new CustomerNotFoundException("Customer does not exist");
		
		Customer existingCustomer = opt.get();
		
//		Integer cartId = existingCustomer.getCustomerCart().getCartId();
//		
//	Optional<Cart> optCart = cartDao.findById(cartId);
	
//	if(optCart.isPresent()) {
//		Cart cart=optCart.get();
//	}
		
		List<Product> cartProducts = existingCustomer.getCustomerCart().getProducts();
		
		for(Product existing : cartProducts) {
			if(product.getProductId() == existing.getProductId()) {
				cartProducts.remove(existing);
			}
		}
		return cartDao.save(existingCustomer.getCustomerCart());
		
		
	}
}
