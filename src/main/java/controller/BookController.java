package controller;

import dao.BookDao;
import modele.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by Pc on 30.07.2017.
 */
public class BookController extends HttpServlet {
    private BookDao dao;
    private static String LIST_BOOK = "/listBook.jsp";
    private static String UPDATE_BOOK = "/updateBook.jsp";

    public BookController() {
        this.dao = new BookDao();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("review")) {
            forward = LIST_BOOK;
            int userid = Integer.parseInt(req.getParameter("userid"));
            req.setAttribute("books", dao.getBooksByUserId(userid));
        } else if (action.equalsIgnoreCase("delete")) {
            int bookid = Integer.parseInt(req.getParameter("bookid"));
            int userid = Integer.parseInt(req.getParameter("userid"));
            dao.deleteBookOne(bookid);
            forward = LIST_BOOK;
            req.setAttribute("books", dao.getBooksByUserId(userid));
        } else if (action.equalsIgnoreCase("edit")) {
            forward = UPDATE_BOOK;
            int bookid = Integer.parseInt(req.getParameter("bookid"));
            Book book = dao.getBookId(bookid);
            req.setAttribute("book", book);
        } else if (action.equalsIgnoreCase("listBook")) {
            forward = LIST_BOOK;
            int userid = Integer.parseInt(req.getParameter("userid"));
            req.setAttribute("books", dao.getBooksByUserId(userid));

        } else if (action.equalsIgnoreCase("reviewAll")) {
            forward = LIST_BOOK;
            req.setAttribute("books", dao.getAllBooks());
        } else {
            forward = UPDATE_BOOK;
        }

        RequestDispatcher rd = req.getRequestDispatcher(forward);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setGenre(req.getParameter("genre"));
        String bookid = req.getParameter("bookId");
        if (bookid == null || bookid.isEmpty()) {
            dao.addBook(book);
        } else {
            book.setBookId(Integer.parseInt(bookid));
            dao.updateBook(book);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(LIST_BOOK);
        req.setAttribute("books", dao.getBooksByUserId(0));
        requestDispatcher.forward(req, resp);


    }
}