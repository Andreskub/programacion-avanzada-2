package edu.usal.controller.admin;

import edu.usal.dao.ClientDAO;
import edu.usal.domain.Client;
import edu.usal.factory.ClientDAOFactory;
import edu.usal.factory.DatabaseDialectType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addClient")
public class AddClientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/addClient.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int dni = Integer.parseInt(request.getParameter("dni"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");

        Client client = new Client();
        client.setDni(dni);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPhone(phone);

        try {
            ClientDAO clientDAO = ClientDAOFactory.createClientDAO(DatabaseDialectType.SQL);
            clientDAO.addClient(client);
            response.sendRedirect("adminMenu");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

