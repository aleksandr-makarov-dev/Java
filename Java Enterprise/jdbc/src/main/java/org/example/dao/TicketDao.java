package org.example.dao;

import org.example.exceptions.DaoException;
import org.example.models.Ticket;
import org.example.utils.ConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {
    private final static TicketDao INSTANCE = new TicketDao();

    public List<Ticket> findAll(){
        try(var conn = ConnectionManager.get();
            var stmt = conn.prepareStatement("SELECT id, passenger_passport, passenger_name, flight_id, seat_no, aircraft_id, cost FROM tickets")
        ){
            var result = stmt.executeQuery();

            List<Ticket> foundTickets = new ArrayList<>();

            while (result.next()){
                foundTickets.add(new Ticket(
                        result.getLong("id"),
                        result.getString("passenger_passport"),
                        result.getString("passenger_name"),
                        result.getLong("flight_id"),
                        result.getString("seat_no"),
                        result.getLong("aircraft_id"),
                        result.getBigDecimal("cost")
                ));
            }

            return foundTickets;

        }catch(SQLException e){
            throw  new DaoException(e);
        }
    }

    public static TicketDao getInstance(){
        return  INSTANCE;
    }

    private TicketDao(){

    }
}
