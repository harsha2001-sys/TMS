package com.sbtaskmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbtaskmanagementsystem.entity.TStatus;
import com.sbtaskmanagementsystem.entity.Task;
import com.sbtaskmanagementsystem.exception.ExceptionClass;
import com.sbtaskmanagementsystem.service.EmployeeServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(name = "employee")

public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@PutMapping(value = "updateEmployeePassword/{employeeId}/{newPassword}")
	public ResponseEntity<String> updateEmployeePassword(@PathVariable int employeeId,@PathVariable String newPassword) throws ExceptionClass {
		return new ResponseEntity<String>(employeeServiceImpl.updateEmployeePassword(employeeId, newPassword),HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "getTaskByEmployeeId/{employeeId}")
	public ResponseEntity<List<Task>> getTaskByEmployeeId(@PathVariable int employeeId) throws ExceptionClass {
		return new ResponseEntity<List<Task>>(employeeServiceImpl.getTaskByEmployeeId(employeeId),HttpStatus.OK);
	}
	
	@GetMapping(value = "employeeLogin/{email}/{password}")
	public ResponseEntity<Integer> employeeLogin(@PathVariable String email,@PathVariable String password) throws ExceptionClass{
		return new ResponseEntity<Integer>(employeeServiceImpl.employeeLogin(email, password), HttpStatus.OK);	
	}
    
	@PutMapping(value = "updateTaskStatusbyId/{taskId}/{statusUpdate}")
	public ResponseEntity<Boolean> updateTaskStatusbyId(@PathVariable int taskId,@PathVariable TStatus statusUpdate) throws ExceptionClass{
		return new ResponseEntity<Boolean>(employeeServiceImpl.updateTaskStatusbyId(taskId, statusUpdate),HttpStatus.OK);
	}	
}
