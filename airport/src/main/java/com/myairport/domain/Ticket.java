package com.myairport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Ticket.
 */
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "seat_class")
    private String seatClass;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "ticket")
    private Set<Passenger> passengers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "ticketFS", allowSetters = true)
    private Flight flight;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public Ticket seatClass(String seatClass) {
        this.seatClass = seatClass;
        return this;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public Double getPrice() {
        return price;
    }

    public Ticket price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public Ticket passengers(Set<Passenger> passengers) {
        this.passengers = passengers;
        return this;
    }

    public Ticket addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
        passenger.setTicket(this);
        return this;
    }

    public Ticket removePassenger(Passenger passenger) {
        this.passengers.remove(passenger);
        passenger.setTicket(null);
        return this;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    public Ticket flight(Flight flight) {
        this.flight = flight;
        return this;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        return id != null && id.equals(((Ticket) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ticket{" +
            "id=" + getId() +
            ", seatClass='" + getSeatClass() + "'" +
            ", price=" + getPrice() +
            "}";
    }
}
