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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tickets")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Long id;
	
	
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern="dd-mm-yy")
	@Column(name = "creat_at")
	private Date creatAt;
	
	@ManyToOne(fetch= FetchType.LAZY)
	private Employee employee;
	
	@OneToMany(fetch= FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="ticket_id")
	private List<ItemTicket> items; 
	
	

	public Ticket() {
		this.items=new ArrayList<ItemTicket>();
	}
	



	@PrePersist
	public void prePersist() {
		creatAt = new Date();
	}

	
	//Mehid AddItemTicket
	
	public void addItemTicket (ItemTicket item) {
		this.items.add(item);
	}
	
	// Method getTotal ticket
	public Double getTotal (ItemTicket item) {
		Double total=0.0;
		int size = items.size();
		for(int i=0; i<size;i++) {
			total+= items.get(i).calculateAmount(); 
		}
		return total;
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatAt() {
		return creatAt;
	}

	public void setCreatAt(Date creatAt) {
		this.creatAt = creatAt;
	}

	
	public Employee getEmployee() {
		return employee;
	}




	public void setEmployee(Employee employee) {
		this.employee = employee;
	}




	public List<ItemTicket> getItems() {
		return items;
	}

	public void setItems(List<ItemTicket> items) {
		this.items = items;
	}
	

	
	
	
	
	
	
	

}
