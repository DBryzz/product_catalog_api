package com.gotraining.productcatalogapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gotraining.productcatalogapi.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
