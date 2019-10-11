package com.gotraining.productcatalogapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotraining.productcatalogapi.dao.CategoryRepository;
import com.gotraining.productcatalogapi.entity.Category;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
    public Optional<Category> getCategoryById(int id){
    	return categoryRepository.findById(id);
    }
    public  void  deleteCategoryById(int id){
    	  categoryRepository.deleteById(id);
    }
    
    public Category createCategory(Category newcateogry) {
        return categoryRepository.save(newcateogry);
    }
    
    public Category updateCategory(Category newcateogry) {
        return categoryRepository.save(newcateogry);
    }
     
	
}
