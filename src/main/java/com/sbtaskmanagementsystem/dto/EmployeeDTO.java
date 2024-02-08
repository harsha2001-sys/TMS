package com.sbtaskmanagementsystem.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EmployeeDTO {
	
	private int employeeId;
	private String employeeName;
	private String emailId;
	private String userName;
    
}
