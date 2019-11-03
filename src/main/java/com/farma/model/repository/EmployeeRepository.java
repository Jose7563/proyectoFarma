package com.farma.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.farma.model.entity.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{

}
