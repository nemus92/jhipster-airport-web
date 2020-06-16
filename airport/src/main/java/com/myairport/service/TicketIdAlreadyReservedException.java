package com.myairport.service;

public class TicketIdAlreadyReservedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TicketIdAlreadyReservedException() {
		super("Given ticket already reserved by passenger!");
	}

}
