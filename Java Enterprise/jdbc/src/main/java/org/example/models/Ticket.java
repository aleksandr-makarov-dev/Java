package org.example.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket {
    private Long id;
    private String passport;
    private String name;
    private Long flightId;
    private String seat;
    private Long aircraftId;
    private BigDecimal cost;

    public Ticket(){

    }

    public Ticket(Long id, String passport, String name, Long flightId, String seat, Long aircraftId, BigDecimal cost) {
        this.id = id;
        this.passport = passport;
        this.name = name;
        this.flightId = flightId;
        this.seat = seat;
        this.aircraftId = aircraftId;
        this.cost = cost;
    }

    public Ticket(String passport, String name, Long flightId, String seat, Long aircraftId, BigDecimal cost) {
        this.passport = passport;
        this.name = name;
        this.flightId = flightId;
        this.seat = seat;
        this.aircraftId = aircraftId;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(passport, ticket.passport) && Objects.equals(name, ticket.name) && Objects.equals(flightId, ticket.flightId) && Objects.equals(seat, ticket.seat) && Objects.equals(cost, ticket.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passport, name, flightId, seat, cost);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", passport='" + passport + '\'' +
                ", name='" + name + '\'' +
                ", flightId=" + flightId +
                ", seat='" + seat + '\'' +
                ", cost=" + cost +
                '}';
    }
}
