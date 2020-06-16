package com.myairport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Flight.
 */
@Entity
@Table(name = "flight")
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "departure")
    private ZonedDateTime departure;

    @Column(name = "arrival")
    private ZonedDateTime arrival;

    @OneToMany(mappedBy = "flight")
    private Set<Ticket> ticketFS = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "flightAirplanes", allowSetters = true)
    private Airplane airplane;

    @ManyToOne
    @JsonIgnoreProperties(value = "flightAirports", allowSetters = true)
    private Airport airport;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDeparture() {
        return departure;
    }

    public Flight departure(ZonedDateTime departure) {
        this.departure = departure;
        return this;
    }

    public void setDeparture(ZonedDateTime departure) {
        this.departure = departure;
    }

    public ZonedDateTime getArrival() {
        return arrival;
    }

    public Flight arrival(ZonedDateTime arrival) {
        this.arrival = arrival;
        return this;
    }

    public void setArrival(ZonedDateTime arrival) {
        this.arrival = arrival;
    }

    public Set<Ticket> getTicketFS() {
        return ticketFS;
    }

    public Flight ticketFS(Set<Ticket> tickets) {
        this.ticketFS = tickets;
        return this;
    }

    public Flight addTicketF(Ticket ticket) {
        this.ticketFS.add(ticket);
        ticket.setFlight(this);
        return this;
    }

    public Flight removeTicketF(Ticket ticket) {
        this.ticketFS.remove(ticket);
        ticket.setFlight(null);
        return this;
    }

    public void setTicketFS(Set<Ticket> tickets) {
        this.ticketFS = tickets;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public Flight airplane(Airplane airplane) {
        this.airplane = airplane;
        return this;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airport getAirport() {
        return airport;
    }

    public Flight airport(Airport airport) {
        this.airport = airport;
        return this;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flight)) {
            return false;
        }
        return id != null && id.equals(((Flight) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Flight{" +
            "id=" + getId() +
            ", departure='" + getDeparture() + "'" +
            ", arrival='" + getArrival() + "'" +
            "}";
    }
}
