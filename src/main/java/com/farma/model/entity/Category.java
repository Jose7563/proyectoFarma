package com.farma.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;

@Entity
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ; 
	
	@NotEmpty(message="El nombre no puede estar vacio")
	@Column(name="name")
	private String name; 
	
	@Lob
	@NotEmpty(message="El campo no puede de descripcion no puede estar  vacio")
	@Type(type = "org.hibernate.type.TextType")
	@Column(name="description")
	private String description;
	
//	@Column(name="url_img")
//	private String urlImg; 
	
	@ManyToMany(mappedBy = "categories")
	private List<Product> products;
	
	public Category() {
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}

	 
	
	
	
	
	
	
	
	
	
	

}
