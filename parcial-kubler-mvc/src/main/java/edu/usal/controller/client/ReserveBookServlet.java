package edu.usal.controller.client;

import edu.usal.service.ReservationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reserveBook")
public class ReserveBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getRequestDispatcher("/jsp/reserveBook.jsp").forward(request, response);

        // Grab cookie
        Cookie[] cookies = request.getCookies();
        String userType = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userType")) {
                    userType = cookie.getValue();
                    break;
                }
            }
        }

        if ("client".equals(userType)) {
            request.getRequestDispatcher("/jsp/reserveBook.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html?error=Unauthorized access.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String clientDni = (String) request.getSession().getAttribute("user");
        ReservationService reservationService = new ReservationService();

        try {
            String message = reservationService.reserveBook(isbn, clientDni);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/jsp/reserveBook.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
