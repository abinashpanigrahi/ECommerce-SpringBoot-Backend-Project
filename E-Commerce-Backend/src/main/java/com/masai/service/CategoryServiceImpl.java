package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.models.Category;
import com.masai.repository.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao catDao;
	
	@Override
	public Category addCategory(Category category) {
		
		Category category1 = catDao.save(category);
		
		return category1;
	}
	
	
}
