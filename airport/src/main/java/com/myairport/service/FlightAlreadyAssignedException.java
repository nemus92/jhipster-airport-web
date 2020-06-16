package com.myairport.service;

public class FlightAlreadyAssignedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FlightAlreadyAssignedException() {
		super("Flight with given id has already been assigned to given ticket!");
	}
}
