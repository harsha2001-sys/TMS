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
public class ManagerDTO {
	
	private int managerId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String userName;
 
}
