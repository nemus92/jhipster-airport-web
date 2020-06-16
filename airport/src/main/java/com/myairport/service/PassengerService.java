package com.myairport.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myairport.domain.Passenger;
import com.myairport.domain.Ticket;
import com.myairport.repository.PassengerRepository;
import com.myairport.repository.TicketRepository;
import com.myairport.service.dto.FlightDTO;
import com.myairport.service.dto.ReservationDTO;

/**
 * Service class for managing passengers.
 */
@Service
@Transactional
public class PassengerService {

    private final PassengerRepository passengerRepository; 
    
    private final TicketRepository ticketRepository;
    
    public PassengerService(PassengerRepository passengerRepository, TicketRepository ticketRepository) {
    	this.passengerRepository = passengerRepository;
    	this.ticketRepository = ticketRepository;
    }
    
    public void reserveTicket(ReservationDTO reservationDTO) {

    	Optional<Passenger> passenger = passengerRepository.findById(reservationDTO.getIdPassenger());
    	
    	Optional<Ticket> ticket = ticketRepository.findById(reservationDTO.getIdTicket());
    	
    	if (!passenger.isPresent()) {
    		throw new PassengerIdNotExistsException();
    	}
    	
    	if (!ticket.isPresent()) {
    		throw new TicketIdNotExistsException();
    	}
    	
    	if (passenger.isPresent() && ticket.isPresent()) {
    		if (passenger.get().getTicket() != null) {
    			if (passenger.get().getTicket().getId().equals(reservationDTO.getIdTicket())) {
    				throw new TicketIdAlreadyReservedException(); 
    			}
    		}
    		
    		passenger.get().setTicket(ticket.get());
    	}
    }
    
    public List<Passenger> getAllPassengersOnFlight(FlightDTO fDTO) {
    	return passengerRepository.findAll().stream()
    				.filter(passenger -> passenger.getTicket() != null)
    				.filter(passenger -> passenger.getTicket().getFlight() != null)
    				.filter(passenger -> passenger.getTicket().getFlight().getId().equals(fDTO.getIdFlight()))
    				.collect(Collectors.toList());
    }

}
