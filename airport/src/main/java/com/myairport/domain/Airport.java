package com.myairport.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Airport.
 */
@Entity
@Table(name = "airport")
public class Airport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    @OneToMany(mappedBy = "airport")
    private Set<Flight> flightAirports = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Airport name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public Airport location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Flight> getFlightAirports() {
        return flightAirports;
    }

    public Airport flightAirports(Set<Flight> flights) {
        this.flightAirports = flights;
        return this;
    }

    public Airport addFlightAirport(Flight flight) {
        this.flightAirports.add(flight);
        flight.setAirport(this);
        return this;
    }

    public Airport removeFlightAirport(Flight flight) {
        this.flightAirports.remove(flight);
        flight.setAirport(null);
        return this;
    }

    public void setFlightAirports(Set<Flight> flights) {
        this.flightAirports = flights;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Airport)) {
            return false;
        }
        return id != null && id.equals(((Airport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Airport{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
