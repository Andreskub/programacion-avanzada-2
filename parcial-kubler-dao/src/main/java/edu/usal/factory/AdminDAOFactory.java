package edu.usal.factory;

import edu.usal.dao.AdminDAO;
import edu.usal.dao.impl.AdminDAOImpl;

import java.sql.SQLException;

public class AdminDAOFactory {

    public static AdminDAO createAdminDAO(DatabaseDialectType type) throws SQLException {

        switch (type) {
            case SQL:
                return new AdminDAOImpl();
            default:
                return null;
        }

    }
}
