package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CartItemNotFound;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.LoginException;
import com.masai.exception.ProductNotFoundException;
import com.masai.models.Cart;
import com.masai.models.CartDTO;
import com.masai.models.Customer;
import com.masai.models.Product;
import com.masai.models.UserSession;
import com.masai.repository.CartDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.ProductDao;
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
	
	
	@Autowired
	private ProductDao productDao;

	@Override
	public Cart addProductToCart(CartDTO cartDto, String token) {

		
		if(token.contains("customer") == false) {
			throw new LoginException("Invalid session token for customer");
		}
		
		loginService.checkTokenStatus(token);
		
		UserSession user = sessionDao.findByToken(token).get();
		
		Optional<Customer> opt = customerDao.findById(user.getUserId());
		
		if(opt.isEmpty())
			throw new CustomerNotFoundException("Customer does not exist");
		
		Customer existingCustomer = opt.get();
		
		System.out.println(existingCustomer);
			
		System.out.println(existingCustomer.getCustomerCart());
			
		Product product = productDao.findById(cartDto.getProductId()).orElseThrow( () -> new ProductNotFoundException("Product not found"));
		
//		cartDto.setProductName(product.getProductName());
//		
//		cartDto.setPrice(product.getPrice());
		
		Product copyProduct = new Product();
		
		copyProduct.setProductId(product.getProductId());
		
		copyProduct.setProductName(product.getProductName());
		
		copyProduct.setDescription(product.getDescription());
		
		copyProduct.setManufacturer(product.getManufacturer());
		
		copyProduct.setPrice(product.getPrice());
		
		copyProduct.setCategory(product.getCategory());
		
//		copyProduct.setSeller(product.getSeller());
		
		copyProduct.setStatus(product.getStatus());
		
		copyProduct.setQuantity(1);
		
			
		List<Product> cartProducts = existingCustomer.getCustomerCart().getProducts();
		
		if(cartProducts.size() == 0) {
			cartProducts.add(copyProduct);
			existingCustomer.getCustomerCart().setProducts(cartProducts);
			System.out.println(existingCustomer);
			cartDao.save(existingCustomer.getCustomerCart());
			return  existingCustomer.getCustomerCart();
		}
			
		else {
		
			for(Product existing : cartProducts) {
				if(copyProduct.getProductId() == existing.getProductId()) {
					existing.setQuantity(existing.getQuantity() + 1);
					existingCustomer.getCustomerCart().setProducts(cartProducts);
					cartDao.save(existingCustomer.getCustomerCart());
					return  existingCustomer.getCustomerCart();
					
				}
			}
		}
		
		System.out.println(existingCustomer);
		
		cartProducts.add(copyProduct);
		existingCustomer.getCustomerCart().setProducts(cartProducts);
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
		
		System.out.println(existingCustomer);
		
		System.out.println(existingCustomer.getCustomerCart());
		
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
