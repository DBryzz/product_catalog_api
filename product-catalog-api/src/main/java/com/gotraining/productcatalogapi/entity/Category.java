package com.gotraining.productcatalogapi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int categoryid;
	
	private String categoryname;
	
	
	
	
	public Category() {
		super();
	}
	

	public Category(int categoryid) {
		super();
		this.categoryid = categoryid;
	}

	public Category(int categoryid, String categoryname) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	@JsonIgnore
	private List<Product> productlist;
	
	public List<Product> getProductlist() {
		return productlist;
	}


	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}


	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", categoryname=" + categoryname + "]";
	}
	
	
	
	
	
	
}
