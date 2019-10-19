package com.farma.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "products")
public class Product {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@NotEmpty
	@Column(name = "name")
	private String name; 
	
	@NotEmpty
	@Column(name = "ubication")
	private String ubication;
	
	@NotEmpty
	@Column(name = "quantity")
	private Long quantity;
	
	@NotEmpty
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern="dd-mm-yy")
	@Column(name = "expiration_date")
	private Date expiration_date;
	
	@NotEmpty
	@Column(name = "unit_price")
	private Double  unit_price;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToMany
	@JoinTable(
			name="provider_product",
			joinColumns= {@JoinColumn(name="product_id")},
			inverseJoinColumns= {@JoinColumn(name="provider_id")}
			)
	private List<Provider> providers;
	
	
	@ManyToMany
	@JoinTable(
			name="product_order",
			joinColumns= {@JoinColumn(name="product_id")},
			inverseJoinColumns= {@JoinColumn(name="order_id")}
			)
	private List<Order> orders;


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


	public String getUbication() {
		return ubication;
	}


	public void setUbication(String ubication) {
		this.ubication = ubication;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public Date getExpiration_date() {
		return expiration_date;
	}


	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}


	public Double getUnit_price() {
		return unit_price;
	}


	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public List<Provider> getProviders() {
		return providers;
	}


	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}	
	
	

	
	
	

}
