package com.masai.service;

import java.util.List;

import com.masai.models.CategoryEnum;
import com.masai.models.Product;
import com.masai.models.ProductDTO;

public interface ProductService {

	public Product addProductToCatalog(Product product);

	public Product getProductFromCatalogById(Integer id);

	public String deleteProductFromCatalog(Integer id);

	public Product updateProductIncatalog(Product product);
	
	public List<Product> getAllProductsIncatalog();
	
	public List<ProductDTO> getProductsOfCategory(CategoryEnum catenum);
	

}
