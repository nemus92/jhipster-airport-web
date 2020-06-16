package com.myairport.service.dto;

import javax.validation.constraints.NotNull;

public class FlightAndTicketDTO {
	
	@NotNull
	private Long idFlight;
	
	@NotNull
	private Long idTicket;

	public Long getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(Long idFlight) {
		this.idFlight = idFlight;
	}

	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}

}
