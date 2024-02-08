package com.sbtaskmanagementsystem.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sbtaskmanagementsystem.entity.Manager;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(value = ExceptionClass.class)
	public ResponseEntity<String> globalEntity(Exception exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		String errorMessage = "Validation failed: " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> listConstraintViolations = ex.getConstraintViolations(); 																					// message is set in the
																								
		List<String> errorList = new ArrayList<>();
		int i = 1;
		for (ConstraintViolation<?> constraintViolation : listConstraintViolations) {
			errorList.add("Validation " + i + ": " + constraintViolation.getMessageTemplate());
			i += 1;
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorList);
	}
}
