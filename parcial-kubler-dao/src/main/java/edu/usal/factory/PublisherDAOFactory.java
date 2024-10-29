package edu.usal.factory;

import edu.usal.dao.PublisherDAO;
import edu.usal.dao.impl.PublisherDAOImpl;

import java.sql.SQLException;

public class PublisherDAOFactory {

    public static PublisherDAO createPublisherDAO(DatabaseDialectType type) throws SQLException {

        switch (type) {
            case SQL:
                return new PublisherDAOImpl();
            default:
                return null;
        }

    }
}
