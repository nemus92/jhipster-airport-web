package com.myairport.repository;

import com.myairport.domain.Passenger;
import com.myairport.domain.User;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Passenger entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	
    @Query("SELECT p FROM Passenger p WHERE p.firstName LIKE %:firstName%  and p.lastName LIKE %:lastName%")
    List<Passenger> findPassengersByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
