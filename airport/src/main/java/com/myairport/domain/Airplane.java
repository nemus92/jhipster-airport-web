package com.myairport.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Airplane.
 */
@Entity
@Table(name = "airplane")
public class Airplane implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "airplane")
    private Set<Flight> flightAirplanes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Airplane code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Flight> getFlightAirplanes() {
        return flightAirplanes;
    }

    public Airplane flightAirplanes(Set<Flight> flights) {
        this.flightAirplanes = flights;
        return this;
    }

    public Airplane addFlightAirplane(Flight flight) {
        this.flightAirplanes.add(flight);
        flight.setAirplane(this);
        return this;
    }

    public Airplane removeFlightAirplane(Flight flight) {
        this.flightAirplanes.remove(flight);
        flight.setAirplane(null);
        return this;
    }

    public void setFlightAirplanes(Set<Flight> flights) {
        this.flightAirplanes = flights;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Airplane)) {
            return false;
        }
        return id != null && id.equals(((Airplane) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Airplane{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            "}";
    }
}
