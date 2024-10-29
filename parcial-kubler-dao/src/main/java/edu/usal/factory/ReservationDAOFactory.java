package edu.usal.factory;

import edu.usal.dao.ReservationDAO;
import edu.usal.dao.impl.ReservationDAOImpl;

import java.sql.SQLException;

public class ReservationDAOFactory {

    public static ReservationDAO createReservationDAO(DatabaseDialectType type) throws SQLException {

        switch (type) {
            case SQL:
                return new ReservationDAOImpl();
            default:
                return null;
        }

    }
}
