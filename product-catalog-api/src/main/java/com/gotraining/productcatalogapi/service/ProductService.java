package com.gotraining.productcatalogapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotraining.productcatalogapi.dao.ProductRepository;
import com.gotraining.productcatalogapi.entity.Product;

@Service
public class ProductService {

	
	@Autowired
	ProductRepository  productRepository;
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	  public Optional<Product> getProductById(int id){
	    	return productRepository.findById(id);
	    }
	  
	  public  void  deleteProductById(int id){
		  productRepository.deleteById(id);
    }
	  
	  public Product createProduct(Product newproduct) {
	        return productRepository.save(newproduct);
	    }
	  public Product updateProduct(Product newproduct) {
	        return productRepository.save(newproduct);
	    }
}
