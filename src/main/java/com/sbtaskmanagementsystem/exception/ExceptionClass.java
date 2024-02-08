package com.sbtaskmanagementsystem.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExceptionClass extends Exception {
	private String errorMessage;

	@Override
	public String getMessage() {
		return this.errorMessage;
	}

	public ExceptionClass() {
		super();
	}
}
