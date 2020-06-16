package com.myairport.repository;

import com.myairport.domain.Passenger;
import com.myairport.domain.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Ticket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	@Query("SELECT t FROM Ticket t INNER JOIN Passenger p ON p.ticket.id = t.id")
    List<Ticket> getAllReserved();
	
}
