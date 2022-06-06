package com.masai.service;

import java.util.List;
import java.util.Map;

import com.masai.models.Cart;
import com.masai.models.Customer;
import com.masai.models.Product;
import com.masai.models.ProductDTO;



public interface CartService {
	
	public Cart addProductToCart(Customer customer,Product product,String token,Integer quantity);
	public List<Product> getCartProduct(Customer customer,String token);
	public Cart removeProductFromCart(Customer customer,Product product,String token);
//	public Cart changeQuantity(Product product,Customer customer,Integer quantity);
	
		
	
}
