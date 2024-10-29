package edu.usal.dao;

import edu.usal.domain.Reservation;

import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO {

    void addReservation(Reservation reservation) throws SQLException;
    Reservation getReservation(int id) throws SQLException;
    List<Reservation> getAllReservations() throws SQLException;
    void approveReservation(int reservationId) throws SQLException;
}
