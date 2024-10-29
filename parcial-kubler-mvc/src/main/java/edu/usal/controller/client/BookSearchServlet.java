package edu.usal.controller.client;

import edu.usal.dao.BookDAO;
import edu.usal.domain.Book;
import edu.usal.factory.BookDAOFactory;
import edu.usal.factory.DatabaseDialectType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/searchBooks")
public class BookSearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/searchBook.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        BookDAO bookDAO = null;
        List<Book> books;

        try {
            bookDAO = BookDAOFactory.createBookDAO(DatabaseDialectType.SQL);
            books = bookDAO.searchBooksByTitleOrISBN(query);
            request.setAttribute("books", books);
            request.getRequestDispatcher("/jsp/searchBook.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
