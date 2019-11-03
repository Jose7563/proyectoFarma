package com.farma.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "providers")
public class Provider {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	
	@NotEmpty(message="El nombre no del proveedor no debe de estar vacio")
	@Column(name="name")
	 private String name; 
	
	@NotNull(message="El email no puede ser nullo")
	@Email
	@Column(name="email")
	 private String email;
	
	@NotNull(message="El campo deltelefono no pude estar vacio")
	@Column(name="phone")
	 private Long phone; 
	
	
	@NotNull
	@Min(11)
	@Column(name="ruc")
	 private Long ruc;
	
	@ManyToMany(mappedBy = "providers")
	private List<Product> products;

	
	
	public Provider() {
	}

	public Provider(Long id, String name, String email, Long phone, Long ruc, List<Product> products) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.ruc = ruc;
		this.products = products;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Long getRuc() {
		return ruc;
	}

	public void setRuc(Long ruc) {
		this.ruc = ruc;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	

	
	

}
