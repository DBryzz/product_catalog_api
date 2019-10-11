package com.gotraining.productcatalogapi.dao;





import org.springframework.data.jpa.repository.JpaRepository;
import com.gotraining.productcatalogapi.entity.Category;



public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
