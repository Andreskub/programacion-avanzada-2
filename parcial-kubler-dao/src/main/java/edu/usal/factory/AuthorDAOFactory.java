package edu.usal.factory;

import edu.usal.dao.AuthorDAO;
import edu.usal.dao.impl.AuthorDAOImpl;

import java.sql.SQLException;

public class AuthorDAOFactory {

    public static AuthorDAO createAuthorDAO(DatabaseDialectType type) throws SQLException {

        switch (type) {
            case SQL:
                return new AuthorDAOImpl();
            default:
                return null;
        }

    }
}
