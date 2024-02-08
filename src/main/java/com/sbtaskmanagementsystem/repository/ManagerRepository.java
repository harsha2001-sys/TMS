package com.sbtaskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbtaskmanagementsystem.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{
	 
}
