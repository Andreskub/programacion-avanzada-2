package edu.usal.dao.impl;

import edu.usal.dao.AuthorDAO;
import edu.usal.domain.Author;
import edu.usal.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthorDAOImpl implements AuthorDAO {

    private Connection connection;

    public AuthorDAOImpl() throws SQLException {
        connection = MySQLConnection.getInstance().getConnection();
    }

    @Override
    public Author getAuthorById(int id) throws SQLException {
        String sql = "SELECT * FROM authors WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setFirstName(rs.getString("firstName"));
                author.setLastName(rs.getString("lastName"));
                return author;
            }
        }
        return null;
    }
}
