package com.sbtaskmanagementsystem.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ManagerDTO2 {
	
	private int managerId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;

}
