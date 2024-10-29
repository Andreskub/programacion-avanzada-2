package edu.usal.dao;

import edu.usal.domain.Admin;
import edu.usal.exceptions.DatabaseInsertException;

import java.sql.SQLException;
import java.util.List;

public interface AdminDAO {

    boolean login(String dni, String password) throws SQLException;

    //Statements
    void addAdmin(Admin admin) throws DatabaseInsertException, SQLException;
    void deleteAdmin(String employeeId) throws SQLException;
    List<Admin> getAllAdminsUsingStatement() throws SQLException;

}
