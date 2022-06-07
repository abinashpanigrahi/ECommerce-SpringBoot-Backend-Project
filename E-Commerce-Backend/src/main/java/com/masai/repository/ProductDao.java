package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.masai.models.Product;
import com.masai.models.ProductDTO;


@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
	
	@Query("select new com.masai.models.ProductDTO(p.productName,p.manufacturer,p.price) "
			+ "from Product p where p.category.categoryId=:catId")
	public List<ProductDTO> getAllProductsInACategory(@Param("catId") Integer catId);
	

}
