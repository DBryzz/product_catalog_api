package com.gotraining.productcatalogapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.gotraining.productcatalogapi.dao.CategoryRepository;
import com.gotraining.productcatalogapi.entity.Category;

@Component
@Order(1)
public class CategoryCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Category Category1=new Category(1,"electronics");
		Category Category2=new Category(2,"cloths");
		Category Category3=new Category(3,"vehicles");
		
		categoryRepository.save(Category1);
		categoryRepository.save(Category2);
		categoryRepository.save(Category3);
		
		
	}
}