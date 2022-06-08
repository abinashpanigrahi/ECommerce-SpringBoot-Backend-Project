package com.masai.service;

import java.util.List;

import com.masai.controller.ProductNotFound;
import com.masai.exception.CartItemNotFound;
import com.masai.models.Cart;
import com.masai.models.CartDTO;
import com.masai.models.CartItem;




public interface CartService {
	
	public Cart addProductToCart(CartDTO cart, String token) throws CartItemNotFound;
	public Cart getCartProduct(String token);
	public Cart removeProductFromCart(CartDTO cartDto,String token) throws ProductNotFound;
//	public Cart changeQuantity(Product product,Customer customer,Integer quantity);
	
	public Cart clearCart(String token);
	
}
