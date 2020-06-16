package com.myairport.web.rest;

import com.myairport.domain.Airport;
import com.myairport.repository.AirportRepository;
import com.myairport.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.myairport.domain.Airport}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AirportResource {

    private final Logger log = LoggerFactory.getLogger(AirportResource.class);

    private static final String ENTITY_NAME = "airport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AirportRepository airportRepository;

    public AirportResource(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    /**
     * {@code POST  /airports} : Create a new airport.
     *
     * @param airport the airport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new airport, or with status {@code 400 (Bad Request)} if the airport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/airports")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) throws URISyntaxException {
        log.debug("REST request to save Airport : {}", airport);
        if (airport.getId() != null) {
            throw new BadRequestAlertException("A new airport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Airport result = airportRepository.save(airport);
        return ResponseEntity.created(new URI("/api/airports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /airports} : Updates an existing airport.
     *
     * @param airport the airport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated airport,
     * or with status {@code 400 (Bad Request)} if the airport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the airport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/airports")
    public ResponseEntity<Airport> updateAirport(@RequestBody Airport airport) throws URISyntaxException {
        log.debug("REST request to update Airport : {}", airport);
        if (airport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Airport result = airportRepository.save(airport);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, airport.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /airports} : get all the airports.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of airports in body.
     */
    @GetMapping("/airports")
    public List<Airport> getAllAirports() {
        log.debug("REST request to get all Airports");
        return airportRepository.findAll();
    }

    /**
     * {@code GET  /airports/:id} : get the "id" airport.
     *
     * @param id the id of the airport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the airport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/airports/{id}")
    public ResponseEntity<Airport> getAirport(@PathVariable Long id) {
        log.debug("REST request to get Airport : {}", id);
        Optional<Airport> airport = airportRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(airport);
    }

    /**
     * {@code DELETE  /airports/:id} : delete the "id" airport.
     *
     * @param id the id of the airport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/airports/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        log.debug("REST request to delete Airport : {}", id);
        airportRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
