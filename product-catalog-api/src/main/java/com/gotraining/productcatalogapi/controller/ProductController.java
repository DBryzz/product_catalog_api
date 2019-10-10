package com.gotraining.productcatalogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotraining.productcatalogapi.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;
	
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
	
	
}
