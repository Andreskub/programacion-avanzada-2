package edu.usal.controller.admin;

import edu.usal.dao.AuthorDAO;
import edu.usal.dao.BookDAO;
import edu.usal.domain.Author;
import edu.usal.domain.Book;
import edu.usal.exceptions.DatabaseInsertException;
import edu.usal.factory.AuthorDAOFactory;
import edu.usal.factory.BookDAOFactory;
import edu.usal.factory.DatabaseDialectType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/addBook.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        int pageCount = Integer.parseInt(request.getParameter("pageCount"));
        String genre = request.getParameter("genre");
        String edition = request.getParameter("edition");
        int availableCopies = Integer.parseInt(request.getParameter("availableCopies"));
        int authorId = Integer.parseInt(request.getParameter("authorId"));

        AuthorDAO authorDAO = null;
        Author author = null;

        try {
            authorDAO = AuthorDAOFactory.createAuthorDAO(DatabaseDialectType.SQL);
            author = authorDAO.getAuthorById(authorId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (author == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Author not found.");
            return;
        }

        Book book = new Book(title, isbn, pageCount, genre, edition, author, new ArrayList<>(), availableCopies);

        BookDAO bookDAO = null;
        try {
            bookDAO = BookDAOFactory.createBookDAO(DatabaseDialectType.SQL);
            bookDAO.addBook(book);
            response.sendRedirect("adminMenu");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (DatabaseInsertException e) {
            throw new RuntimeException(e);
        }
    }
}





