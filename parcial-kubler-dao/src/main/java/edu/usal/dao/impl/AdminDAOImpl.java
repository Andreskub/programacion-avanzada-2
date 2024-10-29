package edu.usal.dao.impl;

import edu.usal.dao.AdminDAO;
import edu.usal.domain.Admin;
import edu.usal.exceptions.DatabaseInsertException;
import edu.usal.utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    private Connection connection;

    public AdminDAOImpl() throws SQLException {
        connection = MySQLConnection.getInstance().getConnection();
    }

    // Use of PreparedStatement
    @Override
    public boolean login(String dni, String password) throws SQLException {

        String sql = "SELECT * FROM admins WHERE dni = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // return true
        }
    }

    // Use of transactions
    @Override
    public void addAdmin(Admin admin) throws DatabaseInsertException, SQLException {
        String sql = "INSERT INTO admins (firstName, lastName, dni, phone, employeeId) VALUES (?, ?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false); // Init Transaction

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, admin.getFirstName());
                stmt.setString(2, admin.getLastName());
                stmt.setString(3, String.valueOf(admin.getDni()));
                stmt.setString(4, admin.getPhone());
                stmt.setString(5, admin.getEmployeeId());
                stmt.executeUpdate();
            }

            connection.commit(); // Confirm Transaction
        } catch (SQLException e) {
            connection.rollback(); // Revert transaction
            throw new DatabaseInsertException("Failed to insert admin: " + admin.getFirstName() + " " + admin.getLastName(), e);
        } finally {
            connection.setAutoCommit(true); // Reset auto-commit
        }
    }

    // Use of Statement
    @Override
    public List<Admin> getAllAdminsUsingStatement() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admins";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setFirstName(rs.getString("firstName"));
                admin.setLastName(rs.getString("lastName"));
                admin.setDni(Integer.valueOf(rs.getString("dni")));
                admin.setPhone(rs.getString("phone"));
                admin.setEmployeeId(rs.getString("employeeId"));
                admins.add(admin);
            }
        }
        return admins;
    }

    // Use of CallableStatement
    @Override
    public void deleteAdmin(String employeeId) throws SQLException {
        String sql = "{CALL deleteAdmin(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, employeeId);
            stmt.execute();
        }
    }

}
