package com.sbtaskmanagementsystem.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.sbtaskmanagementsystem.entity.TStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TaskDTO {
	
	private int taskId;
	private String title;
	private String description;
	private TStatus status;
	private LocalDate startDate;
	private LocalDate endDate;
	private EmployeeDTO employeeDTO;
	
}
