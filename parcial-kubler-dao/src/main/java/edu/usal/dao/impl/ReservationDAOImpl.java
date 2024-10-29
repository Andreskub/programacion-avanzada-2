package edu.usal.dao.impl;

import edu.usal.dao.BookDAO;
import edu.usal.dao.ClientDAO;
import edu.usal.dao.ReservationDAO;
import edu.usal.domain.Book;
import edu.usal.domain.Client;
import edu.usal.domain.Reservation;
import edu.usal.factory.BookDAOFactory;
import edu.usal.factory.ClientDAOFactory;
import edu.usal.factory.DatabaseDialectType;
import edu.usal.utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    private Connection connection;

    public ReservationDAOImpl() throws SQLException {
        connection = MySQLConnection.getInstance().getConnection();
    }

    @Override
    public void addReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservations (book_isbn, client_dni, reservationDate, isApproved, returnDueDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, reservation.getBook().getIsbn());
            stmt.setString(2, String.valueOf(reservation.getClient().getDni()));
            stmt.setDate(3, Date.valueOf(reservation.getReservationDate()));
            stmt.setBoolean(4, reservation.isApproved());
            stmt.setDate(5, Date.valueOf(reservation.getReturnDueDate()));
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Reservation getReservation(int id) throws SQLException {
        String sql = "SELECT * FROM reservations WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));

                String bookIsbn = rs.getString("book_isbn");
                BookDAO bookDAO = BookDAOFactory.createBookDAO(DatabaseDialectType.SQL);
                Book book = bookDAO.getBookByISBN(bookIsbn);
                book.setIsbn(bookIsbn);

                String clientDni = rs.getString("client_dni");
                ClientDAO clientDAO = ClientDAOFactory.createClientDAO(DatabaseDialectType.SQL);
                Client client = clientDAO.getClient(clientDni);
                client.setDni(Integer.valueOf(clientDni));

                reservation.setBook(book);
                reservation.setClient(client);
                reservation.setReservationDate(rs.getDate("reservationDate").toLocalDate());
                reservation.setApproved(rs.getBoolean("isApproved"));
                reservation.setReturnDueDate(rs.getDate("returnDueDate").toLocalDate());
                return reservation;
            }
        }
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));

                String bookIsbn = rs.getString("book_isbn");
                BookDAO bookDAO = BookDAOFactory.createBookDAO(DatabaseDialectType.SQL);
                Book book = bookDAO.getBookByISBN(bookIsbn);

                String clientDni = rs.getString("client_dni");
                ClientDAO clientDAO = ClientDAOFactory.createClientDAO(DatabaseDialectType.SQL);
                Client client = clientDAO.getClient(clientDni);

                reservation.setBook(book);
                reservation.setClient(client);
                reservation.setReservationDate(rs.getDate("reservationDate").toLocalDate());
                reservation.setApproved(rs.getBoolean("isApproved"));
                reservation.setReturnDueDate(rs.getDate("returnDueDate").toLocalDate());

                reservations.add(reservation);
            }
        }
        return reservations;
    }


    @Override
    public void approveReservation(int reservationId) throws SQLException {
        String sql = "UPDATE reservations SET approved = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, true);
            stmt.setInt(2, reservationId);
            stmt.executeUpdate();
        }
    }

}
