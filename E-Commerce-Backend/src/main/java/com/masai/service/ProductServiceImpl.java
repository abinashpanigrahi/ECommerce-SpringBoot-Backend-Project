package com.masai.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.models.Category;
import com.masai.models.Product;
import com.masai.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao prodDao;
	
	
	
	
	@Override
	public Product addProductToCatalog(Product product) {
		
		Product addedProduct = prodDao.save(product);
	
	
		
		return addedProduct;
	}

	@Override
	public Product getProductFromCatalogById(Integer id) {
		
		 Optional<Product> opt =   prodDao.findById(id);
		 
		 return opt.get();
	}

	@Override
	public String deleteProductFromCatalog(Integer id) {
		  Optional<Product> opt  = prodDao.findById(id);
		  
		  Product prod = opt.get();
		  		prodDao.delete(prod);
		
		return "Product deleted from catalog";
	}

	@Override
	public Product updateProductIncatalog(Product prod) {
		
		Optional<Product> opt =  prodDao.findById(prod.getProductId());
		
		if(opt.isPresent()) {
			Product existingProduct = 	opt.get();
			Product prod1 = prodDao.save(prod);
			return prod1;
		}
		
		return null;
	}

	

}
