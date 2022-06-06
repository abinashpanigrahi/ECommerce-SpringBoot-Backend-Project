package com.masai.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.models.Category;
import com.masai.models.Product;
import com.masai.repository.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao catDao;
	
	@Override
	public Category addCategory(Category category) {
		Category cat = null;
		cat = catDao.findByCategoryName(category.getCategoryName());
		System.out.println(cat);
//		Optional<Category> opt =  catDao.findById(category.getCategoryId());
		
		if(cat != null) {
			
			return category;
		}
		else
			cat = catDao.save(category);
		
		return cat;
	}

	
	
	
}
