package com.spring.exceptions;

public class CustomExceptions extends RuntimeException{
	
	String message;

	public CustomExceptions(String message) {
		super(message);
	}
	
}
