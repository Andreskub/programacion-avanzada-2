package edu.usal.dao;

import edu.usal.domain.Author;

import java.sql.SQLException;

public interface AuthorDAO {

    Author getAuthorById(int id) throws SQLException;
}
