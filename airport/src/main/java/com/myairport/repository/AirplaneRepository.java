package com.myairport.repository;

import com.myairport.domain.Airplane;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Airplane entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}
