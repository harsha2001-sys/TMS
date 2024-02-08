package com.sbtaskmanagementsystem.service;

import java.util.List;

import com.sbtaskmanagementsystem.entity.Employee;
import com.sbtaskmanagementsystem.entity.TStatus;
import com.sbtaskmanagementsystem.entity.Task;
import com.sbtaskmanagementsystem.exception.ExceptionClass;

public interface EmployeeService {

	public abstract String updateEmployeePassword(int employeeId, String password) throws ExceptionClass;

	public abstract List<Task> getTaskByEmployeeId(int id) throws ExceptionClass;

	public abstract boolean updateTaskStatusbyId(int taskId, TStatus statusUpdate) throws ExceptionClass;
	
	 public abstract int employeeLogin(String email, String password) throws ExceptionClass;
}
