package edu.usal.dao.impl;

import edu.usal.dao.ClientDAO;
import edu.usal.domain.Client;
import edu.usal.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAOImpl implements ClientDAO {

    private Connection connection;

    public ClientDAOImpl() throws SQLException {
        connection = MySQLConnection.getInstance().getConnection();
    }

    @Override
    public void addClient(Client client) throws SQLException {
        String sql = "INSERT INTO clients (firstName, lastName, dni, phone, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getFirstName());
            stmt.setString(2, client.getLastName());
            stmt.setString(3, String.valueOf(client.getDni()));
            stmt.setString(4, client.getPhone());
            stmt.setString(5, client.getEmail());
            stmt.executeUpdate();
        }
    }

    @Override
    public Client getClient(String dni) throws SQLException {
        String sql = "SELECT * FROM clients WHERE dni = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = new Client();
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setDni(Integer.valueOf(rs.getString("dni")));
                client.setPhone(rs.getString("phone"));
                client.setEmail(rs.getString("email"));
                return client;
            }
        }
        return null;
    }

    @Override
    public boolean login(String dni, String password) throws SQLException {
        String sql = "SELECT * FROM clients WHERE dni = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // return true if exists
        }
    }

}
