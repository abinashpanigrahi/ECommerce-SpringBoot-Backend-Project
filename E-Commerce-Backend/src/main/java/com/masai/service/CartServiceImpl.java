package com.masai.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CartItemNotFound;
import com.masai.models.Cart;
import com.masai.models.Customer;
import com.masai.models.Product;
import com.masai.models.ProductDTO;
import com.masai.repository.CartDao;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cDao;

	@Override
	public Cart addProductToCart(Customer customer, Product product, String token,Integer quantity) {
		
		
		
		Integer cartId = customer.getCart().getCartId();
		
		List<Product> cartProducts = customer.getCart().getProducts();
		
		for(Product existing : cartProducts) {
			if(product.getProductId() == existing.getProductId()) {
				product.setReqQuantity(quantity);
				cDao.save(customer.getCart());
				return  customer.getCart();
				
			}
		}
		cartProducts.add(product);
		cDao.save(customer.getCart());
		return  customer.getCart();
		
		
	}
	
	

	@Override
	public List<Product> getCartProduct(Customer customer, String token) {
		Integer cartId = customer.getCart().getCartId();
		Cart cart = cDao.findbyId(cartId);
		return cart.getProducts();
	}

	
	
	@Override
	public Cart removeProductFromCart(Customer customer, Product product, String token) {
		
		Integer cartId = customer.getCart().getCartId();
		
		Cart cart = cDao.findbyId(cartId);
		
		List<Product> cartProducts = customer.getCart().getProducts();
		
		for(Product existing : cartProducts) {
			if(product.getProductId() == existing.getProductId()) {
				cartProducts.remove(existing);
			}
		}
		return cDao.save(cart);
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//		
//
//	@Override
//	public Cart addProductQuantity(Customer customer, Product product,String token) {
//		
//		
//		Cart customerCart = customer.getCart();
//		Map<Product,Integer> existingCartProduct = customerCart.getProducts();
//		
//		
//		Double totalAmount=0.0;
//		
//		Set<Entry<Product,Integer>> productSet=existingCartProduct.entrySet();
//		for(Entry<Product,Integer> set : productSet) {
//			
//			totalAmount+=set.getKey().getPrice()*set.getKey().getReqQuantity();
//		}
//		customerCart.setTotal(totalAmount);
//		
//		
//		
//		if(existingCartProduct.containsKey(product)) {
//			Integer quantity = existingCartProduct.get(product);
//			quantity++;
//			totalAmount=totalAmount+product.getPrice();
//			existingCartProduct.put(product, quantity);
//		}
//		else {
//			existingCartProduct.put(product,1);
//		}
//		return customerCart;
//	}
//
//	
//	
//	
//	@Override
//	public Map<Product,Integer> getCartProduct(Customer customer,String token) {
//		Cart customerCart = customer.getCart();
//		Map<Product,Integer> existingCartProduct = customerCart.getProducts();
//		return existingCartProduct;
//	}
//
//
//	@Override
//	public Cart removeProductFromCart(Customer customer,Product product,String token) {
//		
//		Cart customerCart = customer.getCart();
//		Map<Product,Integer> existingCartProduct = customerCart.getProducts();
//		
//		if(existingCartProduct.containsKey(product)) {
//			existingCartProduct.remove(product);
//			
//			Double totalAmount=0.0;
//			
//			Set<Entry<Product,Integer>> productSet=existingCartProduct.entrySet();
//			for(Entry<Product,Integer> set : productSet) {
//				totalAmount+=set.getKey().getPrice()*set.getKey().getReqQuantity();
//			}
//			customerCart.setTotal(totalAmount);
//			
//		}
//		return customerCart;
//	}
//
//
//
//
//	@Override
//	public Cart reduceProductQuantity(Customer customer, Product product, String token) {
//		
//		Cart customerCart = customer.getCart();
//		Map<Product,Integer> existingCartProduct = customerCart.getProducts();
//
//		Double totalAmount=0.0;
//		if(existingCartProduct.containsKey(product)) {
//			Integer quantity = existingCartProduct.get(product);
//			if(quantity == 1) {
//				existingCartProduct.remove(product);
//				
//				Set<Entry<Product,Integer>> productSet=existingCartProduct.entrySet();
//				for(Entry<Product,Integer> set : productSet) {
//					totalAmount+=set.getKey().getPrice()*set.getKey().getReqQuantity();
//				}
//				customerCart.setTotal(totalAmount);
//			}
//			else {
//				quantity--;
//				totalAmount=totalAmount-product.getPrice();
//				existingCartProduct.put(product, quantity);
//				
//			}
//		
//		}
//		return customerCart;
//		
//	}
//
//	
//	

	


}
