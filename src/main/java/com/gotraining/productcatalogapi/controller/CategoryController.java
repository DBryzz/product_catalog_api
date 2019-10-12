package com.gotraining.productcatalogapi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotraining.productcatalogapi.entity.Category;
import com.gotraining.productcatalogapi.repository.CategoryRepository;
import com.gotraining.productcatalogapi.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CategoryRepository categoryRepo; 
	
	
	@GetMapping("/category")
	public List<Category> retrieveCategories() {
		return categoryService.getCategories();
	}
	
	@GetMapping("/category/{categoryId}")
	public Resource<Category> retrieveCategory(@PathVariable int categoryId) {
	/*	Optional<Category> category = categoryRepo.findById(categoryId);
		
		if(!category.isPresent()) {
			throw new CategoryNotFoundException("id - " +categoryId);
		}
		
		Resource<Category> resource = new Resource<Category>(category.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(categoryService.getClass()).getCategories());
		resource.add(linkTo.withRel("all-categories"));
		
		return resource; */
		return categoryService.getCategory(categoryId);
	}
	
	@DeleteMapping("/category/{categoryId}")
	public void deleteCategory(@PathVariable int categoryId) {
		categoryService.removeCategory(categoryId);
	}
	
	@PostMapping("/category")
	public ResponseEntity<Object> createCategory(@RequestBody Category category) {
		return categoryService.postCategory(category);
	}
	
	@PutMapping("/category/{categoryId}")
	public ResponseEntity<Object> updateCategory(@PathVariable int categoryId, @RequestBody Category categoryName) {
		return categoryService.editCategory(categoryId, categoryName);
	}

}
