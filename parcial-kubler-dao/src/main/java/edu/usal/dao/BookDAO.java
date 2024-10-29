package edu.usal.dao;

import edu.usal.domain.Book;
import edu.usal.exceptions.DatabaseInsertException;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {

    Book addBook(Book book) throws DatabaseInsertException;
    List<Book> searchBooksByTitleOrISBN(String query) throws SQLException;
    int getAvailableCopyIdByISBN(String isbn) throws SQLException;
    Book getBookByISBN(String isbn) throws SQLException;

}
