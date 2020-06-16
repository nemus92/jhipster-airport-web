package com.myairport.service.dto;

import javax.validation.constraints.NotNull;

public class FlightDTO {

	@NotNull
	private Long idFlight;

	public Long getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(Long idFlight) {
		this.idFlight = idFlight;
	}
}
