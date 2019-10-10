package com.gotraining.productcatalogapi.controller;



import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gotraining.productcatalogapi.entity.Category;
import com.gotraining.productcatalogapi.exception.CategoryNotFoundException;
import com.gotraining.productcatalogapi.service.CategoryService;



@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping(path="/category")
	public  ResponseEntity<Object> getAllCategories()
	{
		return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
	}
	
	@GetMapping(path="/category/{categoryid}")
	public  ResponseEntity<Object> getCategoryById(@PathVariable int categoryid)
	{
		Optional<Category> category =categoryService.getCategoryById(categoryid);
		if(!category.isPresent())
			throw new CategoryNotFoundException("category-id"+categoryid+" not found");
		return new ResponseEntity<>(categoryService.getCategoryById(categoryid),HttpStatus.OK);
	}
	
	@DeleteMapping(path="/category/{categoryid}")
	public ResponseEntity<Object> deleteCategoryById(@PathVariable int categoryid)
	{
	categoryService.deleteCategoryById(categoryid);
	return  ResponseEntity.noContent().build();
	}
	
	@PostMapping(path="/category")
	public ResponseEntity<Object> CreateNewCategory(@RequestBody Category category){
		Category cat= categoryService.createCategory(category) ;
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getCategoryid()).toUri();
		return new ResponseEntity<>(location,HttpStatus.CREATED);	
		
	}
	
	@PutMapping(path="/category/{categoryid}")
	public ResponseEntity<Object> updateCategory(@PathVariable int categoryid,@RequestBody Category categoryname){
		Optional<Category> category=categoryService.getCategoryById(categoryid);
		Category categoryupdate=category.get();
		categoryupdate.setCategoryname(categoryname.getCategoryname());
	    return new ResponseEntity<>(categoryService.updateCategory(categoryupdate),HttpStatus.NO_CONTENT);
	
	} 
	
	
}
