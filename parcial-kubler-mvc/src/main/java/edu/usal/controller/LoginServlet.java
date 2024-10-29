package edu.usal.controller;

import edu.usal.dao.AdminDAO;
import edu.usal.dao.ClientDAO;

import edu.usal.factory.AdminDAOFactory;
import edu.usal.factory.ClientDAOFactory;
import edu.usal.factory.DatabaseDialectType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dni = request.getParameter("dni");
        String password = request.getParameter("password");
        ClientDAO clientDAO = null;
        AdminDAO adminDAO = null;

        try {
            clientDAO = ClientDAOFactory.createClientDAO(DatabaseDialectType.SQL);
            adminDAO = AdminDAOFactory.createAdminDAO(DatabaseDialectType.SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean isClient = false;
        boolean isAdmin = false;

        try {
            isClient = clientDAO.login(dni, password);
            isAdmin = adminDAO.login(dni, password);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        if (isClient) {
            request.getSession().setAttribute("user", dni);

            //Set cookie
            Cookie userTypeCookie = new Cookie("userType", "client");
            userTypeCookie.setMaxAge(60 * 60 * 24); // 1 day
            response.addCookie(userTypeCookie);

            response.sendRedirect("jsp/searchBook.jsp");
        } else if (isAdmin) {
            request.getSession().setAttribute("admin", dni);

            //Set cookie
            Cookie userTypeCookie = new Cookie("userType", "admin");
            userTypeCookie.setMaxAge(60 * 60 * 24); // 1 day
            response.addCookie(userTypeCookie);

            response.sendRedirect("jsp/adminDashboard.jsp");
        } else {
            response.sendRedirect("index.html?error=Invalid credentials.");
        }
    }
}
