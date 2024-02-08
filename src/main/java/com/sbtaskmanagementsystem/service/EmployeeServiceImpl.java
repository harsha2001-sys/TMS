package com.sbtaskmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbtaskmanagementsystem.dto.EmployeeDTO;
import com.sbtaskmanagementsystem.entity.Employee;
import com.sbtaskmanagementsystem.entity.Manager;
import com.sbtaskmanagementsystem.entity.TStatus;
import com.sbtaskmanagementsystem.entity.Task;
import com.sbtaskmanagementsystem.exception.ExceptionClass;
import com.sbtaskmanagementsystem.repository.EmployeeRepository;
import com.sbtaskmanagementsystem.repository.ManagerRepository;
import com.sbtaskmanagementsystem.repository.TaskRepository;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public String updateEmployeePassword(int employeeId, String password) throws ExceptionClass {
		Optional<Employee> optional = employeeRepository.findById(employeeId);

		if (optional.isPresent()) {
			optional.get().setPassword(password);
			employeeRepository.save(optional.get());
		} else {
			throw new ExceptionClass("Employee not found");
		}
		return "Password changed successfully";
	}

	@Override
	public List<Task> getTaskByEmployeeId(int empId) throws ExceptionClass {
		Optional<Employee> optional = employeeRepository.findById(empId);
		if (optional.isPresent()) {
			List<Task> tasks = optional.get().getTasks();
			if (tasks.isEmpty()) {
				throw new ExceptionClass("No tasks found");
			}
			return tasks;
		} else {
			throw new ExceptionClass("Employee not found");
		}
	}

	@Override
	public boolean updateTaskStatusbyId(int taskId, TStatus statusUpdate) throws ExceptionClass {

		Optional<Task> taskOptional = taskRepository.findById(taskId);

		if (taskOptional.isPresent()) {
			    Task task = taskOptional.get();
				task.setStatus(statusUpdate);
				taskRepository.save(task);
		} else {
			throw new ExceptionClass("Task not found");
		}
		return true;
	}

	@Override
	public int employeeLogin(String email, String password) throws ExceptionClass {
		List<Employee> employeesList = employeeRepository.findAll();

		for (Employee employee : employeesList) {
			if (employee.getEmailId().equals(email) && employee.getPassword().equals(password)) {
				return employee.getEmployeeId();
			}
		}
		throw new ExceptionClass("Invalid email or password");
	}

}
