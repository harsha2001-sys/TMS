package com.sbtaskmanagementsystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.sbtaskmanagementsystem.dto.EmployeeDTO;
import com.sbtaskmanagementsystem.dto.ManagerDTO;
import com.sbtaskmanagementsystem.dto.ManagerDTO2;
import com.sbtaskmanagementsystem.dto.TaskDTO;
import com.sbtaskmanagementsystem.entity.Employee;
import com.sbtaskmanagementsystem.entity.Manager;
import com.sbtaskmanagementsystem.entity.TStatus;
import com.sbtaskmanagementsystem.entity.Task;
import com.sbtaskmanagementsystem.exception.ExceptionClass;
import com.sbtaskmanagementsystem.repository.EmployeeRepository;
import com.sbtaskmanagementsystem.repository.ManagerRepository;
import com.sbtaskmanagementsystem.repository.TaskRepository;

//Hello

@Component
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public String createManager(ManagerDTO2 manager) throws ExceptionClass {

		if (managerRepository.existsById(manager.getManagerId())) {
			throw new ExceptionClass("Manager with the same ID already exists");
		}

		String userName = "";
		int atIndex = manager.getEmailId().indexOf('@');

		if (atIndex != -1) {
			userName = manager.getEmailId().substring(0, atIndex);
		}

		Manager managerDto = new Manager(manager.getManagerId(), manager.getFirstName(), manager.getLastName(),
				manager.getEmailId(), userName, manager.getPassword(), null, null);
		managerRepository.save(managerDto);

		return "Manager created successfully";
	}

	@Override
	public String createEmployee(EmployeeDTO employee, int managerId) throws ExceptionClass {
		int employeeId = employee.getEmployeeId();
		Optional<Manager> optional = managerRepository.findById(managerId);

		if (optional.isPresent()) {
			if (employeeRepository.existsById(employeeId)) {
				throw new ExceptionClass("Employee with the same ID already exists");
			}

			Employee emp = new Employee(employeeId, employee.getEmployeeName(), employee.getEmailId(),
					employee.getUserName(), employee.getEmployeeName() + 123, optional.get(), null);

			employeeRepository.save(emp);

		} else {
			throw new ExceptionClass("Manager not found");
		}
		return "Employee created successfully";
	}

	@Override
	public String createTask(TaskDTO task, int managerId) throws ExceptionClass {

		Optional<Manager> optional = managerRepository.findById(managerId);
		if (optional.isPresent()) {

			if ((task.getStartDate().isAfter(LocalDate.now()) || task.getStartDate().equals(LocalDate.now())
					|| !task.getStartDate().isBefore(LocalDate.now()))
					&& (!task.getEndDate().isBefore(LocalDate.now()) || task.getEndDate().isAfter(LocalDate.now())
							|| task.getEndDate().equals(LocalDate.now()))) {
				Task taskEmp = new Task(task.getTaskId(), task.getTitle(), task.getDescription(), task.getStatus(),
						task.getStartDate(), task.getEndDate(), optional.get(), null);
				taskRepository.save(taskEmp);
				return "Task created successfully";
			} else {
				throw new ExceptionClass("Invalid start date or end date");
			}
		} else {
			throw new ExceptionClass("Manager not found");
		}
	}

	@Override
	public boolean assignTaskforEmployee(String employeeName, String title) throws ExceptionClass {

		Employee employee = employeeRepository.findByEmployeeName(employeeName);

		Optional<Task> optTask = taskRepository.findByTitle(title);
		if (optTask.isPresent()) {

			Task task = optTask.get();
			if (!task.getStatus().equals(TStatus.COMPLETED)) {
				if (task.getTaskEmployee() == null) {
					task.setTaskEmployee(employee);
					taskRepository.save(task);
					employeeRepository.save(employee);
					return true;
				}

				else {
					throw new ExceptionClass("Task already assigned to an employee");
				}
			} else {
				throw new ExceptionClass("Completed task cannot be assigned");
			}
		} else {
			throw new ExceptionClass("Task Not Found");
		}
	}

	@Override
	public List<EmployeeDTO> getEmployeeByManagerId(int managerid) throws ExceptionClass {

		Optional<Manager> optional = managerRepository.findById(managerid);
		if (optional.isPresent()) {
			List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();
			for (Employee emp : optional.get().getEmployees()) {
				EmployeeDTO empDto = mapper.map(emp, EmployeeDTO.class);
				employees.add(empDto);
			}
			if (employees.isEmpty()) {
				throw new ExceptionClass("No Employees");
			}
			return employees;
		} else {
			throw new ExceptionClass("Manager not found");
		}
	}

	@Override
	public List<TaskDTO> getAssignedTaskByManagerId(int managerid) throws ExceptionClass {
		Optional<Manager> optional = managerRepository.findById(managerid);

		if (optional.isPresent()) {
			Manager manager = optional.get();
			List<TaskDTO> tasks = new ArrayList<TaskDTO>();

			for (Task task : manager.getTasks()) {
				TaskDTO taskDto = mapper.map(task, TaskDTO.class);
				if (task.getTaskEmployee() != null) {
					EmployeeDTO empDto = mapper.map(task.getTaskEmployee(), EmployeeDTO.class);
					taskDto.setEmployeeDTO(empDto);
				}
				tasks.add(taskDto);
			}
			if (!tasks.isEmpty()) {
				return tasks;
			} else {
				throw new ExceptionClass("No tasks");
			}
		} else {
			throw new ExceptionClass("Manager not found");
		}
	}

	@Override
	public boolean updateEmployeeForTask(String employeeName, String title) throws ExceptionClass {

		Employee employee = employeeRepository.findByEmployeeName(employeeName);
		Optional<Task> optTask = taskRepository.findByTitle(title);
		if (optTask.isPresent()) {
			Task task = optTask.get();
			if (!employee.getTasks().contains(task)) {
				if (task.getStatus() == TStatus.COMPLETED) {
					throw new ExceptionClass("Completed task cannot be updated");
				}
				task.setStatus(TStatus.ACTIVE);
				task.setTaskEmployee(employee);
				taskRepository.save(task);
				return true;
			} else {
				throw new ExceptionClass("Task already assigned");
			}

		} else {
			throw new ExceptionClass("Task Not Found");
		}
	}

	@Override
	public String updateEndDate(LocalDate endDate, int taskId) throws ExceptionClass {
		Optional<Task> taskOptional = taskRepository.findById(taskId);
		if (taskOptional.isPresent()) {
			Task task = taskOptional.get();
			if (endDate.isAfter(LocalDate.now())) {
				task.setEndDate(endDate);
				taskRepository.save(task);
			} else {
				throw new ExceptionClass("Invalid End Date");
			}
		} else {
			throw new ExceptionClass("Task not found");
		}
		return "End date updated successfully";
	}

	@Override
	public String deleteTaskByEmployeeId(int empId, int taskId) throws ExceptionClass {
		Optional<Employee> empOptional = employeeRepository.findById(empId);
		Optional<Task> taskOptional = taskRepository.findById(taskId);

		if (empOptional.isPresent()) {
			Employee employee = empOptional.get();
			if (taskOptional.isPresent()) {
				Task task = taskOptional.get();
				employee.getTasks().remove(task);
				employeeRepository.save(employee);
				taskRepository.delete(task);
			} else {
				throw new ExceptionClass("Task not found");
			}
		} else {
			throw new ExceptionClass("Employee not found");
		}
		return "Task deleted successfully";
	}

	@Override
	public boolean deleteEmployeeById(int empId) throws ExceptionClass {
		Optional<Employee> empOptional = employeeRepository.findById(empId);
		if (empOptional.isPresent()) {
			Employee employee = empOptional.get();
			employeeRepository.deleteById(empId);
			return true;
		} else {
			throw new ExceptionClass("Employee not found");
		}
	}

	@Override
	public int managerLogin(String email, String password) throws ExceptionClass {
		List<Manager> managerList = managerRepository.findAll();

		for (Manager manager : managerList) {
			if (manager.getEmailId().equals(email) && manager.getPassword().equals(password)) {
				return manager.getManagerId();
			}
		}
		throw new ExceptionClass("Invalid email or password");
	}

	public String editEmployee(EmployeeDTO employee, int employeeId) throws ExceptionClass {
		Optional<Employee> empOptional = employeeRepository.findById(employeeId);

		if (empOptional.isPresent()) {
			Employee existingEmployee = empOptional.get();
//	        existingEmployee.setEmployeeId(employee.getEmployeeId());
			existingEmployee.setEmployeeName(employee.getEmployeeName());
			existingEmployee.setEmailId(employee.getEmailId());
			existingEmployee.setUserName(employee.getUserName());
			employeeRepository.save(existingEmployee);
			return "Employee updated successfully";
		} else {
			throw new ExceptionClass("Employee not found ");
		}
	}

	@Override
	public String editTask(TaskDTO task, int taskId) throws ExceptionClass {
		Optional<Task> taskOptional = taskRepository.findById(taskId);

		if (taskOptional.isPresent()) {
			Task existingTask = taskOptional.get();

			// Check if the new start date and end date are valid
			if ((task.getStartDate().isAfter(LocalDate.now()) || task.getStartDate().equals(LocalDate.now())
					|| !task.getStartDate().isBefore(LocalDate.now()))
					&& (!task.getEndDate().isBefore(LocalDate.now()) || task.getEndDate().isAfter(LocalDate.now())
							|| task.getEndDate().equals(LocalDate.now()))) {
				existingTask.setTitle(task.getTitle());
				existingTask.setDescription(task.getDescription());
				existingTask.setStatus(task.getStatus());
				existingTask.setStartDate(task.getStartDate());
				existingTask.setEndDate(task.getEndDate());

				taskRepository.save(existingTask);
				return "Task updated successfully";
			} else {
				throw new ExceptionClass("Invalid start date or end date");
			}
		} else {
			throw new ExceptionClass("Task not found");
		}
	}

	@Override
	public Employee getEmployeebyEmployeeId(int employeeId) throws ExceptionClass {
		Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

		if (employeeOptional.isPresent()) {
			return employeeOptional.get();
		} else {
			throw new ExceptionClass("Employee not found");
		}
	}

	@Override
	public Task getTaskbyTaskId(int taskId) throws ExceptionClass {
		Optional<Task> taskOptional = taskRepository.findById(taskId);

		if (taskOptional.isPresent()) {
			return taskOptional.get();
		} else {
			throw new ExceptionClass("Task not found");
		}
	}

	@Override
	public boolean deleteTaskById(int taskId) throws ExceptionClass {
		Optional<Task> taskOptional = taskRepository.findById(taskId);
		if (taskOptional.isPresent()) {
			Task task = taskOptional.get();
			taskRepository.deleteById(taskId);
			return true;
		} else {
			throw new ExceptionClass("Task not found");
		}
	}

	@Override
	public Manager getManagerbyManagerId(int managerid) throws ExceptionClass {
		Optional<Manager> managerOptional = managerRepository.findById(managerid);
		if (managerOptional.isPresent()) {
			return managerOptional.get();
		} else {
			throw new ExceptionClass("Manager not found");
		}
	}

	@Override
	public List<String> getAllEmployeesName() throws ExceptionClass {
		List<Employee> list = employeeRepository.findAll();
		List<String> listOfNames = new ArrayList<String>();
		for (Employee employee : list) {
			listOfNames.add(employee.getEmployeeName());
		}
		return listOfNames;
	}

	@Override
	public List<String> getAllTasksName() throws ExceptionClass {
		List<Task> list = taskRepository.findAll();
		List<String> listOfNames = new ArrayList<String>();
		for (Task task : list) {
			listOfNames.add(task.getTitle());
		}
		return listOfNames;
	}
}