package com.sbtaskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbtaskmanagementsystem.entity.Employee;
import com.sbtaskmanagementsystem.entity.Manager;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	Employee findByEmployeeName(String name);
	 
}
