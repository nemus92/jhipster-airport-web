package com.myairport.service.dto;

import javax.validation.constraints.NotNull;

public class ReservationDTO {
	
	@NotNull
	private Long idTicket;
	
	@NotNull
	private Long idPassenger;

	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}

	public Long getIdPassenger() {
		return idPassenger;
	}

	public void setIdPassenger(Long idPassenger) {
		this.idPassenger = idPassenger;
	}

}
