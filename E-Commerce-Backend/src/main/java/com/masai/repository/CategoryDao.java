package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.models.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
		public Category findByCategoryName(String categoryName);
}
