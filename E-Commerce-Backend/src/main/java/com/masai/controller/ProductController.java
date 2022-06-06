package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Category;
import com.masai.models.Product;
import com.masai.service.CategoryService;
import com.masai.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
	
	
	
	//this method adds new product to catalog by seller and returns added product
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProductToCatalogHandler(@Valid @RequestBody Product product){
		
		Product prod = pService.addProductToCatalog(product);
		
		return new ResponseEntity<Product>(prod,HttpStatus.ACCEPTED);
		
	}
	
	
	//This method gets the product which needs to be added to the cart returns product
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductFromCatalogByIdHandler(@PathVariable("id") Integer id){
		
		Product prod = pService.getProductFromCatalogById(id);
		
		return new ResponseEntity<Product>(prod,HttpStatus.FOUND);
		
	}
	
	
	//This method will delete the product from catalog and returns the response 
	//This will be called only when the product qty will be zero or seller wants to delete for any other reason
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProductFromCatalogHandler(@PathVariable("id") Integer id){
		
		String res = pService.deleteProductFromCatalog(id);
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
	
	public ResponseEntity<Product> updateProductInCatalogHandler(@Valid @RequestBody Product prod){
		
				Product prod1 = pService.updateProductIncatalog(prod);
				
				return new ResponseEntity<Product>(prod1,HttpStatus.OK);
		
	}
	
	
	
	
	
}
