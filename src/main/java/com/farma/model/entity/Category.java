package com.farma.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ; 
	
	@Column(name="name")
	private String name; 
	
	@Column(name="description")
	private String description;
	
	@Column(name="url_img")
	private String urlImg; 
	
	public Category() {
	}

	public Category(Long id, String name, String description, String urlImg) {
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.urlImg = urlImg;
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

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	} 
	
	
	
	
	
	
	
	
	
	

}
