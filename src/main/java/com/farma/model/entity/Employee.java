package com.farma.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long id;
	
	@NotEmpty
	@Column(name="name")
	private String name;
	 
	@NotEmpty
	@Column(name="lastname")
	private String lastName;
	
	@NotNull
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern="dd-mm-yy")
	@Column(name="creat_At")
	private Date creatAt; 

	@OneToMany( mappedBy="employee",fetch= FetchType.LAZY,cascade=CascadeType.ALL)
	public List<Ticket> tickets;
	
	
	//Metethod addTicket
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
	
	
	public Employee() {
		tickets = new ArrayList<Ticket>();
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Date getCreatAt() {
		return creatAt;
	}

	public void setCreatAt(Date creatAt) {
		this.creatAt = creatAt;
	}
	
	
	
	

}
