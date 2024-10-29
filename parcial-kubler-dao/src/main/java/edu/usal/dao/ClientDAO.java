package edu.usal.dao;

import edu.usal.domain.Client;

import java.sql.SQLException;

public interface ClientDAO {

    boolean login(String dni, String password) throws SQLException;
    void addClient(Client client) throws SQLException;
    Client getClient(String dni) throws SQLException;

}
