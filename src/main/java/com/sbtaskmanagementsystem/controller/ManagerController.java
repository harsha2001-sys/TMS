package com.sbtaskmanagementsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbtaskmanagementsystem.dto.EmployeeDTO;
import com.sbtaskmanagementsystem.dto.ManagerDTO;
import com.sbtaskmanagementsystem.dto.ManagerDTO2;
import com.sbtaskmanagementsystem.dto.TaskDTO;
import com.sbtaskmanagementsystem.entity.Employee;
import com.sbtaskmanagementsystem.entity.Manager;
import com.sbtaskmanagementsystem.entity.Task;
import com.sbtaskmanagementsystem.exception.ExceptionClass;
import com.sbtaskmanagementsystem.repository.ManagerRepository;
import com.sbtaskmanagementsystem.service.ManagerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(name = "manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ManagerRepository managerRepository;

	@PostMapping(value = "createManager")
	public ResponseEntity<String> createManager(@RequestBody ManagerDTO2 manager) throws ExceptionClass {
		return new ResponseEntity<String>(managerService.createManager(manager), HttpStatus.CREATED);
	}

	@PostMapping(value = "createEmployee/{managerId}")
	public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO employee, @PathVariable int managerId)
			throws ExceptionClass {
		return new ResponseEntity<String>(managerService.createEmployee(employee, managerId), HttpStatus.CREATED);
	}

	@PostMapping(value = "createTask/{managerId}", consumes = "application/json")
	public ResponseEntity<String> createTask(@RequestBody TaskDTO task, @PathVariable int managerId)
			throws ExceptionClass {
		return new ResponseEntity<String>(managerService.createTask(task, managerId), HttpStatus.CREATED);
	}

	@PostMapping(value = "editEmployee/{employeeId}", consumes = "application/json")
	public ResponseEntity<String> editEmployee(@RequestBody EmployeeDTO employee, @PathVariable int employeeId)
			throws ExceptionClass {
		return new ResponseEntity<String>(managerService.editEmployee(employee, employeeId), HttpStatus.CREATED);

	}

	@PostMapping(value = "editTask/{taskId}", consumes = "application/json")
	public ResponseEntity<String> editTask(@RequestBody TaskDTO task, @PathVariable int taskId) throws ExceptionClass {
		return new ResponseEntity<String>(managerService.editTask(task, taskId), HttpStatus.CREATED);

	}

	@PostMapping(value = "assignTaskforEmployee/{employeeName}/{title}")
	public ResponseEntity<Boolean> assignTaskforEmployee(@PathVariable String employeeName, @PathVariable String title)
			throws ExceptionClass {
		return new ResponseEntity<Boolean>(managerService.assignTaskforEmployee(employeeName, title),
				HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "getEmployeesByManagerId/{managerId}", produces = "application/json")
	public ResponseEntity<List<EmployeeDTO>> getEmployeesByManagerId(@PathVariable int managerId)
			throws ExceptionClass {
		return new ResponseEntity<List<EmployeeDTO>>(managerService.getEmployeeByManagerId(managerId), HttpStatus.OK);
	}

	@GetMapping(value = "getAssignedTaskByManagerId/{managerId}", produces = "application/json")
	public ResponseEntity<List<TaskDTO>> getAssignedTaskByManagerId(@PathVariable int managerId) throws ExceptionClass {
		return new ResponseEntity<List<TaskDTO>>(managerService.getAssignedTaskByManagerId(managerId), HttpStatus.OK);
	}

	@GetMapping(value = "managerLogin/{email}/{password}")
	public ResponseEntity<Integer> managerLogin(@PathVariable String email, @PathVariable String password)
			throws ExceptionClass {
		return new ResponseEntity<Integer>(managerService.managerLogin(email, password), HttpStatus.OK);
	}

	@GetMapping(value = "getEmployeeByEmployeeId/{employeeId}")
	public ResponseEntity<Employee> getEmployeeByEmployeeId(@PathVariable int employeeId) throws ExceptionClass {
		return new ResponseEntity<Employee>(managerService.getEmployeebyEmployeeId(employeeId), HttpStatus.OK);
	}
	
	@GetMapping(value = "getManagerbyManagerId")
	public ResponseEntity<Manager> getManagerbyManagerId(@RequestParam int managerid) throws ExceptionClass {
		return new ResponseEntity<Manager>(managerService.getManagerbyManagerId(managerid), HttpStatus.OK);
	}

	@GetMapping(value = "getTaskByTaskId/{taskId}")
	public ResponseEntity<Task> getTaskByTaskId(@PathVariable int taskId) throws ExceptionClass {
		return new ResponseEntity<Task>(managerService.getTaskbyTaskId(taskId), HttpStatus.OK);
	}
	
	@GetMapping(value = "getAllEmployeesName")
	public ResponseEntity<List<String>> getAllEmployeesName() throws ExceptionClass {
		return new ResponseEntity<List<String>>(managerService.getAllEmployeesName(),HttpStatus.OK);
	}
	
	@GetMapping(value = "getAllTasksName")
	public ResponseEntity<List<String>> getAllTasksName() throws ExceptionClass {
		return new ResponseEntity<List<String>>(managerService.getAllTasksName(),HttpStatus.OK);
	}

	@PostMapping(value = "updateEmployeeForTask/{employeeName}/{title}")
	public ResponseEntity<Boolean> updateEmployeeForTask(@PathVariable String employeeName,  @PathVariable String title)
			throws ExceptionClass {
		return new ResponseEntity<Boolean>(managerService.updateEmployeeForTask(employeeName, title),
				HttpStatus.ACCEPTED);
	}

	@PutMapping(value = "updateEndDate/{endDate}/{taskId}")
	public ResponseEntity<String> updateEndDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
			@PathVariable int taskId) throws ExceptionClass {
		return new ResponseEntity<String>(managerService.updateEndDate(endDate, taskId), HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "deleteTaskByEmployeeId/{employeeId}/{taskId}")
	public ResponseEntity<String> deleteTaskByEmployeeId(@PathVariable int employeeId, @PathVariable int taskId)
			throws ExceptionClass {
		return new ResponseEntity<String>(managerService.deleteTaskByEmployeeId(employeeId, taskId), HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteEmployeeById/{employeeId}")
	public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable int employeeId) throws ExceptionClass {
		return new ResponseEntity<Boolean>(managerService.deleteEmployeeById(employeeId), HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteTaskById/{taskId}")
	public ResponseEntity<Boolean> deleteTaskById(@PathVariable int taskId) throws ExceptionClass {
		return new ResponseEntity<Boolean>(managerService.deleteTaskById(taskId), HttpStatus.OK);
	}
}
