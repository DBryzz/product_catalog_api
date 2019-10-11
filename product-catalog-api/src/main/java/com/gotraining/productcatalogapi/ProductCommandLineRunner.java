package com.gotraining.productcatalogapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.gotraining.productcatalogapi.dao.ProductRepository;
import com.gotraining.productcatalogapi.entity.Category;
import com.gotraining.productcatalogapi.entity.Product;

@Component
@Order(2)
public class ProductCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Product product1=new Product(1,"laptop",20,2000);
		product1.setCategory(new Category(1));
		productRepository.save(product1);

		Product product2=new Product(2,"hardrive",20,2000);
		product2.setCategory(new Category(2));
		productRepository.save(product2);
		
		Product product3=new Product(3,"shoes",20,2000);
		product3.setCategory(new Category(1));
		productRepository.save(product3);
			
	}
}