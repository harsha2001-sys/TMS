package com.sbtaskmanagementsystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SbtaskmanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbtaskmanagementsystemApplication.class, args);
	}
	
	 @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
	    }
}
