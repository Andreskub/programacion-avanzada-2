package edu.usal.service;

import edu.usal.dao.BookDAO;
import edu.usal.dao.ClientDAO;
import edu.usal.dao.ReservationDAO;
import edu.usal.domain.Book;
import edu.usal.domain.Client;
import edu.usal.domain.Reservation;
import edu.usal.factory.BookDAOFactory;
import edu.usal.factory.ClientDAOFactory;
import edu.usal.factory.ReservationDAOFactory;
import edu.usal.factory.DatabaseDialectType;

import java.sql.SQLException;
import java.time.LocalDate;


public class ReservationService {

    public String reserveBook(String isbn, String clientDni) throws SQLException {
        ReservationDAO reservationDAO = ReservationDAOFactory.createReservationDAO(DatabaseDialectType.SQL);
        BookDAO bookDAO = BookDAOFactory.createBookDAO(DatabaseDialectType.SQL);
        ClientDAO clientDAO = ClientDAOFactory.createClientDAO(DatabaseDialectType.SQL);

        int copyId = bookDAO.getAvailableCopyIdByISBN(isbn);
        if (copyId != -1) {
            Reservation reservation = new Reservation();
            Book book = bookDAO.getBookByISBN(isbn);
            Client client = clientDAO.getClient(clientDni);

            reservation.setBook(book);
            reservation.setClient(client);
            reservation.setReservationDate(LocalDate.now());
            reservation.setApproved(false); // Not approved by defect
            reservation.setReturnDueDate(LocalDate.now().plusDays(5));

            reservationDAO.addReservation(reservation);
            return "Book reserved successfully!";
        } else {
            return "No available copies for the provided ISBN.";
        }
    }
}
