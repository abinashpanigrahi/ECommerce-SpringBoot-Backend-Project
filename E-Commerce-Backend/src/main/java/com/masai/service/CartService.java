package com.masai.service;

import java.util.List;


import com.masai.controller.ProductNotFound;
import com.masai.exception.CartItemNotFound;
import com.masai.models.Cart;

import com.masai.models.Product;




public interface CartService {
	
	public Cart addProductToCart(Product product,String token,Integer quantity) throws CartItemNotFound;
	public List<Product> getCartProduct(String token);
	public Cart removeProductFromCart(Product product,String token) throws ProductNotFound;
//	public Cart changeQuantity(Product product,Customer customer,Integer quantity);
	
		
	
}
