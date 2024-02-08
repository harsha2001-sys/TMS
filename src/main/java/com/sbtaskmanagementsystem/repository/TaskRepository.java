package com.sbtaskmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbtaskmanagementsystem.entity.Manager;
import com.sbtaskmanagementsystem.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	Optional<Task> findByTitle(String title);
	 
}
