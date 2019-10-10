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
import com.gotraining.productcatalogapi.entity.Product;
import com.gotraining.productcatalogapi.service.CategoryService;
import com.gotraining.productcatalogapi.service.ProductService;


@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(path="/products")
	public  ResponseEntity<Object> getAllProducts()
	{
		return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
	}
	
	@GetMapping(path="/products/{productid}")
	public  ResponseEntity<Object> getOneProduct(@PathVariable("productid") int id)
	{
		return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
	}
	
	@DeleteMapping(path="/products/{productid}")
	public ResponseEntity<Object> deleteProductById(@PathVariable("productid")  int id) {
		productService.deleteProductById(id);
		return  ResponseEntity.noContent().build();
	}
	
	@PostMapping(path="/products/category/{categoryid}")
	public ResponseEntity<Object> createProduct(@PathVariable("categoryid") int id,@RequestBody Product productproperties ){
		Optional<Category> category=categoryService.getCategoryById(id);
		Category categroryproperties=category.get();
		productproperties.setCategory(new Category(id,categroryproperties.getCategoryname()));
	    productService.createProduct(productproperties);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("").buildAndExpand().toUri();
	    return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping(path="/products/{productid}/category/{categoryid}")
	public ResponseEntity<Object> updateProduct(@PathVariable("productid") int productid,@PathVariable("categoryid") int categoryid,@RequestBody Product productproperties ){
		
		Optional<Category> category=categoryService.getCategoryById(categoryid);
		Category categroryproperties=category.get();
		Optional<Product> product = productService.getProductById(productid);
		Product updateproduct=product.get();
		updateproduct.setCategory(new Category(productid,categroryproperties.getCategoryname()));
		updateproduct.setPrice(productproperties.getPrice());
		updateproduct.setProductname(productproperties.getProductname());
		updateproduct.setQuantity(productproperties.getQuantity());
		return new ResponseEntity<>(productService.updateProduct(updateproduct),HttpStatus.NO_CONTENT);

		
	}
	
	
	
	
	
}
