package edu.usal.dao.impl;

import edu.usal.dao.AuthorDAO;
import edu.usal.dao.BookDAO;
import edu.usal.dao.PublisherDAO;
import edu.usal.domain.Author;
import edu.usal.domain.Book;
import edu.usal.domain.Publisher;
import edu.usal.exceptions.DatabaseInsertException;
import edu.usal.factory.AuthorDAOFactory;
import edu.usal.factory.DatabaseDialectType;
import edu.usal.factory.PublisherDAOFactory;
import edu.usal.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private Connection connection;

    public BookDAOImpl() throws SQLException {
        connection = MySQLConnection.getInstance().getConnection();
    }

    @Override
    public Book addBook(Book book) throws DatabaseInsertException {
        String sql = "INSERT INTO books (title, isbn, pageCount, genre, edition, availableCopies) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getIsbn());
            stmt.setInt(3, book.getPageCount());
            stmt.setString(4, book.getGenre());
            stmt.setString(5, book.getEdition());
            stmt.setInt(6, book.getAvailableCopies());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseInsertException("Failed to insert the book into the database.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getInt(1));
                    return book;
                } else {
                    throw new DatabaseInsertException("Failed to retrieve the generated book ID.");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseInsertException("Error inserting the book into the database", e);
        }
    }


    @Override
    public List<Book> searchBooksByTitleOrISBN(String query) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR isbn LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // '%' for partial searchs
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setIsbn(rs.getString("isbn"));
                book.setPageCount(rs.getInt("pageCount"));
                book.setGenre(rs.getString("genre"));
                book.setEdition(rs.getString("edition"));
                book.setAvailableCopies(rs.getInt("availableCopies"));
                books.add(book);
            }
        }
        return books;
    }

    @Override
    public int getAvailableCopyIdByISBN(String isbn) throws SQLException {
        String sql = "SELECT id FROM copies WHERE book_isbn = ? AND available = TRUE LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    @Override
    public Book getBookByISBN(String isbn) throws SQLException {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setPageCount(rs.getInt("pageCount"));
                book.setGenre(rs.getString("genre"));
                book.setEdition(rs.getString("edition"));
                book.setAvailableCopies(rs.getInt("availableCopies"));

                int authorId = rs.getInt("author_id");
                int publisherId = rs.getInt("publisher_id");

                AuthorDAO authorDAO = AuthorDAOFactory.createAuthorDAO(DatabaseDialectType.SQL);
                Author author = authorDAO.getAuthorById(authorId);
                book.setAuthor(author);

                PublisherDAO publisherDAO = PublisherDAOFactory.createPublisherDAO(DatabaseDialectType.SQL);
                Publisher publisher = publisherDAO.getPublisherById(publisherId);
                book.setPublisher(publisher);

                return book;
            }
        }
        return null;
    }

}
