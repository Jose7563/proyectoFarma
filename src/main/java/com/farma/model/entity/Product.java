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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "products")
public class Product {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Long id ;
	
	@NotEmpty(message="EL campo nombre no puede estra vacio")
	@Column(name = "name")
	private String name; 
	
	@NotEmpty(message="El campo ubicacion no puede estra vacio")
	@Column(name = "ubication")
	private String ubication;
	
	@NotNull(message="EL campo cantidad no puede estra vacio")
	@Column(name = "quantity")
	private Long quantity;
	
	@NotNull
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern="dd-mm-yy")
	@Column(name = "expiration_date")
	private Date expirationDate;
	
	@NotNull(message="EL campo precio no puede estar vacio")
	@Column(name = "unit_price")
	private Double  unitPrice;
	
	@ManyToMany
	@JoinTable(
			name="product_category",
			joinColumns= {@JoinColumn(name="product_id")},
			inverseJoinColumns= {@JoinColumn(name="category_id")}
			)
	
	private List<Category> categories;
	
	
	@ManyToMany
	@JoinTable(
			name="product_provider",
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
	
	@PrePersist
	public void prePersist() {
		expirationDate = new Date();
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

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
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
