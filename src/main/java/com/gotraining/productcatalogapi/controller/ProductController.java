package com.gotraining.productcatalogapi.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.gotraining.productcatalogapi.exceptions.ResourceNotFoundException;
import com.gotraining.productcatalogapi.repository.CategoryRepository;
import com.gotraining.productcatalogapi.repository.ProductRepository;
import com.gotraining.productcatalogapi.service.CategoryService;
import com.gotraining.productcatalogapi.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	ProductRepository productRepo;

	@GetMapping(path = "/products")
	public ResponseEntity<Object> getAllProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping(path = "/products/{productid}")
	public ResponseEntity<Object> getOneProduct(@PathVariable("productid") int id) {
		Optional<Product> product = productService.getProductById(id);
		if (!product.isPresent()) {
			throw new ResourceNotFoundException("product-id" + id + " not found");
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@DeleteMapping(path = "/products/{productid}")
	public ResponseEntity<Object> deleteProductById(@PathVariable("productid") int id) {
		productService.deleteProductById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(path = "/products/category/{categoryid}")
	public ResponseEntity<Object> createProduct(@PathVariable("categoryid") int id,
			@RequestBody Product productproperties) {
		Optional<Category> category = categoryRepo.findById(id);
		Category categroryproperties = category.get();
		productproperties.setCategory(new Category(id, categroryproperties.getCatName()));
		productService.createProduct(productproperties);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("").buildAndExpand().toUri();
		return ResponseEntity.created(location).build();

	}

	
	@PutMapping(path = "/products/{pxtId}/category/{catId}")
	public ResponseEntity<Object> editProduct(@PathVariable int pxtId, @PathVariable int catId,
			@RequestBody Product newProduct) {

		Optional<Product> product = productRepo.findById(pxtId);
		if (product.isPresent()) {

			Optional<Category> category_1 = categoryRepo.findById(catId);
			if (!category_1.isPresent()) {
				throw new ResourceNotFoundException("category_id - " + catId);
			}

			// newProduct.putCategory(category.get());

			Product productUpdate = product.get();
			productUpdate.setProductname(newProduct.getProductname());
			productUpdate.setQuantity(newProduct.getQuantity());
			productUpdate.setPrice(newProduct.getPrice());
			Category newCat = new Category(category_1.get().getCatId(), category_1.get().getCatName());
			newCat.setCatId(product.get().getCategoryId());
			newCat.setCatName(product.get().getCategoryName());
			productUpdate.putCategory(newCat);

			return new ResponseEntity<>(productRepo.save(productUpdate), HttpStatus.NO_CONTENT);

		}
		throw new ResourceNotFoundException("product_id - " + pxtId);

	} 

	
	  

}
