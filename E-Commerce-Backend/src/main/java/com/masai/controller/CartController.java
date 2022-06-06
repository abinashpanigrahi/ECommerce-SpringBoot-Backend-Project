package com.masai.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Cart;
import com.masai.models.Customer;
import com.masai.models.Product;
import com.masai.repository.CartDao;
import com.masai.repository.CustomerDao;
import com.masai.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cService;
	
	@Autowired
	private CartDao cDao;
	
	@Autowired
	private CustomerDao cuDao;
	

//	@PostMapping(value = "/cart")
//	public ResponseEntity<Cart> addProductToCartHander(@RequestBody Product product ,@RequestHeader("token")String token){
//		
//		
//	}
	
//	
//	@GetMapping(value = "/cart")
//	public ResponseEntity<Cart> getCartProductHandler(){
//		
//	}
//	
	
	
	
	
	
}
