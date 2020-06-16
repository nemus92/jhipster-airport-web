package com.myairport.service;

public class PassengerIdNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PassengerIdNotExistsException() {
		super("Passenger with provided id does not exist!");
	}

}
