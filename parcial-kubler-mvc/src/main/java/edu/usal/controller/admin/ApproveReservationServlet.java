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

@WebServlet("/approveReservation")
public class ApproveReservationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reservationId = request.getParameter("id");

        try {
            ReservationDAO reservationDAO = ReservationDAOFactory.createReservationDAO(DatabaseDialectType.SQL);
            Reservation reservation = reservationDAO.getReservation(Integer.parseInt(reservationId));
            request.setAttribute("reservation", reservation);
            request.getRequestDispatcher("/jsp/approveReservation.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String reservationId = request.getParameter("id");

        try {
            ReservationDAO reservationDAO = ReservationDAOFactory.createReservationDAO(DatabaseDialectType.SQL);
            reservationDAO.approveReservation(Integer.parseInt(reservationId));
            response.sendRedirect("reservationList");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}


