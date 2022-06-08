package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.masai.models.Seller;
import com.masai.models.SellerDTO;
import com.masai.models.SessionDTO;
import com.masai.service.SellerService;

@RestController
public class SellerController {
	
	@Autowired
	private SellerService sService;
	
	
	//Add seller-------------------------------------
	
	@PostMapping("/addseller")
	public ResponseEntity<Seller> addSellerHandler(@Valid @RequestBody Seller seller){
		
		Seller addseller=sService.addSeller(seller);
		
		System.out.println("Seller"+ seller);
		
		return new ResponseEntity<Seller>(addseller,HttpStatus.CREATED);
	}
	
	
	
	//Get the list of seller-----------------------
	
	
	@GetMapping("/sellers")
	public ResponseEntity<List<Seller>> getAllSellerHandler(){
		
		List<Seller> sellers=sService.getAllSellers();
		
		return new ResponseEntity<List<Seller>>(sellers, HttpStatus.OK);
	}
	
	
	//Get the seller by Id............................
	
	
	@GetMapping("/seller/{sellerId}")
	public ResponseEntity<Seller> getSellerByIdHandler(@PathVariable("sellerId") Integer Id){
		
		Seller getSeller=sService.getSellerById(Id);
		
		return new ResponseEntity<Seller>(getSeller, HttpStatus.OK);
	}
	
	
	// Get Seller by mobile Number
	
	@GetMapping("/seller")
	public ResponseEntity<Seller> getSellerByMobileHandler(@RequestParam("mobile") String mobile, @RequestHeader("token") String token){
		
		Seller getSeller=sService.getSellerByMobile(mobile, token);
		
		return new ResponseEntity<Seller>(getSeller, HttpStatus.OK);
	}
	
	
	// Get currently logged in seller
	
	@GetMapping("/seller/current")
	public ResponseEntity<Seller> getLoggedInSellerHandler(@RequestHeader("token") String token){
		
		Seller getSeller = sService.getCurrentlyLoggedInSeller(token);
		
		return new ResponseEntity<Seller>(getSeller, HttpStatus.OK);
	}
	
	//Update the seller..............................
	
	
	@PutMapping("/seller")
	public ResponseEntity<Seller> updateSellerHandler(@RequestBody Seller seller, @RequestHeader("token") String token){
		Seller updatedseller=sService.updateSeller(seller, token);
		
		return new ResponseEntity<Seller>(updatedseller,HttpStatus.ACCEPTED);
		
	}
	
	
	@PutMapping("/seller/update/mobile")
	public ResponseEntity<Seller> updateSellerMobileHandler(@Valid @RequestBody SellerDTO sellerdto, @RequestHeader("token") String token){
		Seller updatedseller=sService.updateSellerMobile(sellerdto, token);
		
		return new ResponseEntity<Seller>(updatedseller,HttpStatus.ACCEPTED);
		
	}
	
	
	@PutMapping("/seller/update/password")
	public ResponseEntity<SessionDTO> updateSellerPasswordHandler(@Valid @RequestBody SellerDTO sellerDto, @RequestHeader("token") String token){
		return new ResponseEntity<>(sService.updateSellerPassword(sellerDto, token), HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/seller/{sellerId}")
	public ResponseEntity<Seller> deleteSellerByIdHandler(@PathVariable("sellerId") Integer Id, @RequestHeader("token") String token){
		
		Seller deletedSeller=sService.deleteSellerById(Id, token);
		
		return new ResponseEntity<Seller>(deletedSeller,HttpStatus.OK);
		
	}
	
	

}
