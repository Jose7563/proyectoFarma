package com.farma.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Long id;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="order_date")
	private Date orderDate;
	
	@ManyToOne
	@JoinColumn(name="provider_id")
	private Provider provider; 
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToMany(mappedBy="orders")
	private List<Product> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	
	

	
}
