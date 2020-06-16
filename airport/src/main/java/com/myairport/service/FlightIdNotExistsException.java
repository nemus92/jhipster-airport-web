package com.myairport.service;

public class FlightIdNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FlightIdNotExistsException() {
		super("Flight with given id does not exist!");
	}
}
