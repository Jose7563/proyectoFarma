package com.farma.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farma.model.entity.Employee;

public interface EmployeeService  extends CrudService<Employee, Long>{
	
	public Page<Employee> findAll(Pageable pageable);
}
