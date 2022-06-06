package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Category;
import com.masai.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService catService;
	
	@PostMapping("/category")
	public ResponseEntity<Category> addCategoryOfProducts(@RequestBody Category category){
		
		Category cate = catService.addCategory(category);
		
		return new ResponseEntity<Category>(cate,HttpStatus.ACCEPTED);
	}

}
