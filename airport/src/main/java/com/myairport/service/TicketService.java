package com.myairport.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myairport.domain.Flight;
import com.myairport.domain.Ticket;
import com.myairport.repository.FlightRepository;
import com.myairport.repository.TicketRepository;
import com.myairport.service.dto.FlightAndTicketDTO;

/**
 * Service class for managing tickets.
 */
@Service
@Transactional
public class TicketService {

	private final FlightRepository flightRepository; 
	
	private final TicketRepository ticketRepository; 
	
	public TicketService(FlightRepository flightRepository, TicketRepository ticketRepository) {
		this.flightRepository = flightRepository;
		this.ticketRepository = ticketRepository;
	}
	
	public void addFlightToTicket(FlightAndTicketDTO ftDTO) {

    	Optional<Flight> flight = flightRepository.findById(ftDTO.getIdFlight());
    	
    	Optional<Ticket> ticket = ticketRepository.findById(ftDTO.getIdTicket());
    	
    	if (!flight.isPresent()) {
    		throw new FlightIdNotExistsException();
    	}
    	
    	if (!ticket.isPresent()) {
    		throw new TicketIdNotExistsException();
    	}
    	
    	if (flight.isPresent() && ticket.isPresent()) {
    		if (ticket.get().getFlight() != null) {
    			if (ticket.get().getFlight().getId().equals(ftDTO.getIdFlight())) {
    				throw new FlightAlreadyAssignedException();
    			}
    		}

    		ticket.get().setFlight(flight.get());
    	}
    }
}
