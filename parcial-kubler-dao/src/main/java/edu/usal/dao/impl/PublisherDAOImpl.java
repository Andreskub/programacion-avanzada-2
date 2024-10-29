package edu.usal.dao.impl;

import edu.usal.dao.PublisherDAO;
import edu.usal.domain.Publisher;
import edu.usal.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherDAOImpl implements PublisherDAO {

    private Connection connection;

    public PublisherDAOImpl() throws SQLException {
        connection = MySQLConnection.getInstance().getConnection();
    }

    @Override
    public Publisher getPublisherById(int publisherId) throws SQLException {
        String sql = "SELECT * FROM publishers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, publisherId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Publisher publisher = new Publisher();
                publisher.setId(rs.getInt("id"));
                publisher.setName(rs.getString("name"));
                publisher.setAddress(rs.getString("address"));
                publisher.setContactNumber(rs.getString("phone"));
                publisher.setEmail(rs.getString("email"));
                return publisher;
            }
        }
        return null;
    }

}
