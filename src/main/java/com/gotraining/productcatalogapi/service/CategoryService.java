package com.gotraining.productcatalogapi.service;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gotraining.productcatalogapi.entity.Category;
import com.gotraining.productcatalogapi.exceptions.ResourceNotFoundException;
import com.gotraining.productcatalogapi.repository.CategoryRepository;

@Service
public class CategoryService {

	private static List<Category> categories = new ArrayList<>();
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	Category category;
	
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}
	
	public Resource<Category> getCategory(int id) {
		Optional<Category> category = categoryRepo.findById(id);
		
		if(!category.isPresent()) {
			throw new ResourceNotFoundException("id - " +id);
		}
		
		Resource<Category> resource = new Resource<Category>(category.get());
		//ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getCategories());
		//resource.add(linkTo.withRel("all-categories"));
		
		return resource;
	}
	
	public void removeCategory(int id) {
		categoryRepo.deleteById(id);
	}
	
	public ResponseEntity<Object> postCategory(Category category) { //@Valid
		Category newCategory = categoryRepo.save(category);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newCategory.getCatId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	public ResponseEntity<Object> editCategory(int id, Category categoryName) {
		Optional<Category> category = categoryRepo.findById(id);
		Category categoryUpdate = category.get();
		categoryUpdate.setCatName(categoryName.getCatName());
		return new ResponseEntity<>(categoryRepo.save(categoryUpdate), HttpStatus.NO_CONTENT);
		
	}
}
