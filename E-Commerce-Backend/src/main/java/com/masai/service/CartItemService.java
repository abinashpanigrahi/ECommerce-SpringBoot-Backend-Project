package com.masai.service;

import com.masai.models.CartDTO;
import com.masai.models.CartItem;

public interface CartItemService {
	
	public CartItem createItemforCart(CartDTO cartdto);
	
}
