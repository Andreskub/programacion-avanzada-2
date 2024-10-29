package edu.usal.factory;

import edu.usal.dao.ClientDAO;
import edu.usal.dao.impl.ClientDAOImpl;

import java.sql.SQLException;

public class ClientDAOFactory {

    public static ClientDAO createClientDAO(DatabaseDialectType type) throws SQLException {

        switch (type) {
            case SQL:
                return new ClientDAOImpl();
            default:
                return null;
        }

    }
}
