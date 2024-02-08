package com.sbtaskmanagementsystem.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "manager_id")
	private int managerId;
  
	@NotEmpty(message = "Enter the Manager name")
	@Size(min = 2, max = 20, message = "Name should be above 2 letters and below 20 letters ")
	private String firstName;
	@Size(min = 2, max = 20, message = "Name should be above 2 letters and below 20 letters ")
	private String lastName;
	
	@Column(length = 30, unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9._%-]{4,}@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,5}$", message = "Check the email address")
	private String emailId;
	
	@Column(unique = true, length = 20)
	private String userName;
	
	@Column(length = 20)
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "managerEmployee")
	@JsonManagedReference
//	@JsonIgnore
	List<Employee> employees;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "managerTask")
//	@JsonIgnore
	private List<Task> tasks;
}
