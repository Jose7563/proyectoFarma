package com.farma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.farma.model.entity.Employee;
import com.farma.model.repository.EmployeeRepository;
import com.farma.service.EmployeeService;
@Service
public class EmployeeServiceImpl  implements  EmployeeService{
	
	@Autowired
	private EmployeeRepository userRespository;

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return  (List<Employee>) userRespository.findAll();
	}

	@Override
	public Employee getOneById(Long id) {
		return userRespository.findById(id)
				.orElseThrow(() -> new RuntimeException("User Not Found!"));
	}

	@Override
	public Long create(Employee entity) {
		// TODO Auto-generated method stub
		userRespository.save(entity);
		
		return entity.getId();
	}

	@Override
	public void update(Long id, Employee entity) {
		
		Employee u = getOneById(id); 
		u.setLastName(entity.getLastName());
		u.setName(entity.getName());
		u.setCreatAt(entity.getCreatAt());
		u.setTickets(entity.getTickets());
		userRespository.save(u); 
		
		
	}

	@Override
	public void delete(Long id) {
		userRespository.deleteById(id);
	}

	@Override
	public Page<Employee> findAll(Pageable pageable) {
		return userRespository.findAll(pageable);
	}
	
	
	
	

}
