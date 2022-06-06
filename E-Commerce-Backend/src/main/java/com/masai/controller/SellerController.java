package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.masai.models.Seller;
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
	
	
	//Update the seller..............................
	
	
	@PutMapping("/seller")
	public ResponseEntity<Seller> updateSellerHandler(@RequestBody Seller seller){
		Seller updatedseller=sService.updateSeller(seller);
		
		return new ResponseEntity<Seller>(updatedseller,HttpStatus.ACCEPTED);
		
	}
	
	
	
	
	@PutMapping("/seller/{sellerId}")
	public ResponseEntity<Seller> updateSellerMobileHandler(@PathVariable("sellerId") Integer Id, @Valid @RequestParam("mobile") String mobile){
		Seller updatedseller=sService.updateSellerMobile(Id, mobile);
		
		return new ResponseEntity<Seller>(updatedseller,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/seller/{sellerId}")
	public ResponseEntity<Seller> deleteSellerByIdHandler(@PathVariable("sellerId") Integer Id){
		
		Seller deletedSeller=sService.deleteSellerById(Id);
		
		return new ResponseEntity<Seller>(deletedSeller,HttpStatus.OK);
		
	}
	
	

}
