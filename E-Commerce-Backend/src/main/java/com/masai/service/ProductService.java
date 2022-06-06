package com.masai.service;

import com.masai.models.Product;

public interface ProductService {

	public Product addProductToCatalog(Product product);

	public Product getProductFromCatalogById(Integer id);

	public String deleteProductFromCatalog(Integer id);

	public Product updateProductIncatalog(Product product);

}
