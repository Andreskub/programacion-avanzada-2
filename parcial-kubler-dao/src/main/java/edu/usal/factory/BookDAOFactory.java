package edu.usal.factory;

import edu.usal.dao.BookDAO;
import edu.usal.dao.impl.BookDAOImpl;

import java.sql.SQLException;

public class BookDAOFactory {

    public static BookDAO createBookDAO(DatabaseDialectType type) throws SQLException {

        switch (type) {
            case SQL:
                return new BookDAOImpl();
            default:
                return null;
        }

    }
}
