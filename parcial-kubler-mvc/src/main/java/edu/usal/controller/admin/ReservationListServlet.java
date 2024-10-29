package edu.usal.controller.admin;

import edu.usal.dao.ReservationDAO;
import edu.usal.domain.Reservation;
import edu.usal.factory.ReservationDAOFactory;
import edu.usal.factory.DatabaseDialectType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/reservationList")
public class ReservationListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ReservationDAO reservationDAO = ReservationDAOFactory.createReservationDAO(DatabaseDialectType.SQL);
            List<Reservation> reservations = reservationDAO.getAllReservations();
            request.setAttribute("reservations", reservations);
            request.getRequestDispatcher("/jsp/reservationList.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

