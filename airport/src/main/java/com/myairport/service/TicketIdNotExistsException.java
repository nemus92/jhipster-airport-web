package com.myairport.service;

public class TicketIdNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TicketIdNotExistsException() {
		super("Ticket with provided id does not exist!");
	}

}
