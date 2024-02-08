package com.sbtaskmanagementsystem.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sbtaskmanagementsystem.dto.EmployeeDTO;
import com.sbtaskmanagementsystem.dto.ManagerDTO;
import com.sbtaskmanagementsystem.dto.ManagerDTO2;
import com.sbtaskmanagementsystem.dto.TaskDTO;
import com.sbtaskmanagementsystem.entity.Employee;
import com.sbtaskmanagementsystem.entity.Manager;
import com.sbtaskmanagementsystem.entity.Task;
import com.sbtaskmanagementsystem.exception.ExceptionClass;

public interface ManagerService {

	public abstract String createManager(ManagerDTO2 manager) throws ExceptionClass;

	public abstract int managerLogin(String email, String password) throws ExceptionClass;

	public abstract String createEmployee(EmployeeDTO employee, int managerId) throws ExceptionClass;
	
	public abstract Employee getEmployeebyEmployeeId(int employeeId) throws ExceptionClass;
	
	public abstract Manager getManagerbyManagerId(int managerid) throws ExceptionClass;
	
	public abstract Task getTaskbyTaskId(int taskId) throws ExceptionClass;
	
	public abstract String editEmployee(EmployeeDTO employee,int employeeId) throws ExceptionClass;
	
	public abstract String editTask(TaskDTO task,int taskId) throws ExceptionClass;

	public abstract String createTask(TaskDTO task, int employeeId) throws ExceptionClass;

	public abstract boolean assignTaskforEmployee(String employeeName, String title) throws ExceptionClass;

	public abstract List<EmployeeDTO> getEmployeeByManagerId(int managerId) throws ExceptionClass;

	public abstract List<TaskDTO> getAssignedTaskByManagerId(int managerId) throws ExceptionClass;

	public abstract boolean updateEmployeeForTask(String employeeName, String title) throws ExceptionClass;

	public abstract String updateEndDate(LocalDate endDate, int taskId) throws ExceptionClass;

	public abstract String deleteTaskByEmployeeId(int empId, int taskId) throws ExceptionClass;

	public abstract boolean deleteEmployeeById(int empId) throws ExceptionClass;
	
	public abstract boolean deleteTaskById(int taskId) throws ExceptionClass;
	
	public abstract List<String> getAllEmployeesName() throws ExceptionClass;
	
	public abstract List<String> getAllTasksName() throws ExceptionClass;

}
