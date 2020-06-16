package com.myairport.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO representing a users name and surname.
 */
public class NameAndSurnameDTO {

	@Size(max = 50)
	@NotNull(message = "Name may not be null")
	private String firstName;
	
	@Size(max = 50)
	@NotNull(message = "Last name may not be null")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
