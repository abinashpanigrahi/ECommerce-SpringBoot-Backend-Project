package com.masai.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.ProductNotFoundException;
import com.masai.models.Category;
import com.masai.models.Product;
import com.masai.models.ProductDTO;
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
	public Product getProductFromCatalogById(Integer id) throws ProductNotFoundException{
		
		 Optional<Product> opt =   prodDao.findById(id);
		 if(opt.isPresent()) {
		 return opt.get();}
		 
		 else
			 throw new ProductNotFoundException("Product not found with given id");
	}

	@Override
	public String deleteProductFromCatalog(Integer id) throws ProductNotFoundException{
		  Optional<Product> opt  = prodDao.findById(id);
		  if(opt.isPresent()) {
		  Product prod = opt.get();
		  		prodDao.delete(prod);
		  		return "Product deleted from catalog";
		  }
		  else
			  throw new ProductNotFoundException("Product not found with given id");
		
	}

	@Override
	public Product updateProductIncatalog(Product prod) throws ProductNotFoundException{
		
		Optional<Product> opt =  prodDao.findById(prod.getProductId());
		
		if(opt.isPresent()) {
			Product existingProduct = opt.get();
			Product prod1 = prodDao.save(prod);
			return prod1;
		}
		else
			throw new ProductNotFoundException("Product not found with given id");
	}

	@Override
	public List<Product> getAllProductsIncatalog() {
		List<Product> list = prodDao.findAll();
		
		if(list.size()>0) {
			return list;
		}
		else
			throw new ProductNotFoundException("No products in catalog");
	
	}

	@Override
	public List<ProductDTO> getProductsOfCategory(Integer id) {

		List<ProductDTO> list = prodDao.getAllProductsInACategory(id);
		if(list.size()>0) {
			
			return list;
		}
		else
			throw new ProductNotFoundException("No products found with category Id:"+id);
	}

	

}
