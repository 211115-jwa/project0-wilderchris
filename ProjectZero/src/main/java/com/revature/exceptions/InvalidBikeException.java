package com.revature.exceptions;

public class InvalidBikeException extends Exception {// InvalidBike custom Exception

	
	private static final long serialVersionUID = 1L;
	

	public InvalidBikeException(String message, Throwable cause) {
		super("Invalid Bike Exception Thrown!!"+ cause);
		
	}
	
	public InvalidBikeException(String message) {
		super("Invalid Bike Exception Thrown!!");
		
	}
	
}
