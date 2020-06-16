package com.myairport.web.rest;

import com.myairport.AirportApp;
import com.myairport.domain.Airplane;
import com.myairport.repository.AirplaneRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AirplaneResource} REST controller.
 */
@SpringBootTest(classes = AirportApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AirplaneResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAirplaneMockMvc;

    private Airplane airplane;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Airplane createEntity(EntityManager em) {
        Airplane airplane = new Airplane()
            .code(DEFAULT_CODE);
        return airplane;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Airplane createUpdatedEntity(EntityManager em) {
        Airplane airplane = new Airplane()
            .code(UPDATED_CODE);
        return airplane;
    }

    @BeforeEach
    public void initTest() {
        airplane = createEntity(em);
    }

    @Test
    @Transactional
    public void createAirplane() throws Exception {
        int databaseSizeBeforeCreate = airplaneRepository.findAll().size();
        // Create the Airplane
        restAirplaneMockMvc.perform(post("/api/airplanes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(airplane)))
            .andExpect(status().isCreated());

        // Validate the Airplane in the database
        List<Airplane> airplaneList = airplaneRepository.findAll();
        assertThat(airplaneList).hasSize(databaseSizeBeforeCreate + 1);
        Airplane testAirplane = airplaneList.get(airplaneList.size() - 1);
        assertThat(testAirplane.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createAirplaneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = airplaneRepository.findAll().size();

        // Create the Airplane with an existing ID
        airplane.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAirplaneMockMvc.perform(post("/api/airplanes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(airplane)))
            .andExpect(status().isBadRequest());

        // Validate the Airplane in the database
        List<Airplane> airplaneList = airplaneRepository.findAll();
        assertThat(airplaneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAirplanes() throws Exception {
        // Initialize the database
        airplaneRepository.saveAndFlush(airplane);

        // Get all the airplaneList
        restAirplaneMockMvc.perform(get("/api/airplanes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(airplane.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getAirplane() throws Exception {
        // Initialize the database
        airplaneRepository.saveAndFlush(airplane);

        // Get the airplane
        restAirplaneMockMvc.perform(get("/api/airplanes/{id}", airplane.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(airplane.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingAirplane() throws Exception {
        // Get the airplane
        restAirplaneMockMvc.perform(get("/api/airplanes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAirplane() throws Exception {
        // Initialize the database
        airplaneRepository.saveAndFlush(airplane);

        int databaseSizeBeforeUpdate = airplaneRepository.findAll().size();

        // Update the airplane
        Airplane updatedAirplane = airplaneRepository.findById(airplane.getId()).get();
        // Disconnect from session so that the updates on updatedAirplane are not directly saved in db
        em.detach(updatedAirplane);
        updatedAirplane
            .code(UPDATED_CODE);

        restAirplaneMockMvc.perform(put("/api/airplanes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAirplane)))
            .andExpect(status().isOk());

        // Validate the Airplane in the database
        List<Airplane> airplaneList = airplaneRepository.findAll();
        assertThat(airplaneList).hasSize(databaseSizeBeforeUpdate);
        Airplane testAirplane = airplaneList.get(airplaneList.size() - 1);
        assertThat(testAirplane.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingAirplane() throws Exception {
        int databaseSizeBeforeUpdate = airplaneRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAirplaneMockMvc.perform(put("/api/airplanes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(airplane)))
            .andExpect(status().isBadRequest());

        // Validate the Airplane in the database
        List<Airplane> airplaneList = airplaneRepository.findAll();
        assertThat(airplaneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAirplane() throws Exception {
        // Initialize the database
        airplaneRepository.saveAndFlush(airplane);

        int databaseSizeBeforeDelete = airplaneRepository.findAll().size();

        // Delete the airplane
        restAirplaneMockMvc.perform(delete("/api/airplanes/{id}", airplane.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Airplane> airplaneList = airplaneRepository.findAll();
        assertThat(airplaneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
