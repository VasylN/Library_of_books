package dao;

import modele.Book;
import modele.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pc on 02.08.2017.
 */
public class BookDao{

    int cacheUserId;
    private static Connection connection;

    public BookDao() {

        connection = DBUtil.getConnection();
    }

    public List<Book> getBooksByUserId(int userid) {
        if (userid != 0) {
            cacheUserId = userid;
        }

        List<Book> books = new ArrayList<Book>();
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT * FROM books WHERE userid= ?");
            pr.setInt(1, cacheUserId);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setBookId(resultSet.getInt("bookid"));
                book.setUserid(resultSet.getInt("userid"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public void addBook(Book book) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO books(author, title, genre, userid) VALUES (?,?,?,?)");
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setInt(4, cacheUserId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookid"));
                book.setGenre(rs.getString("genre"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("title"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public void deleteBook(int userid) {
        try {
            PreparedStatement pr = connection.prepareStatement("DELETE FROM books WHERE userid = ?");
            pr.setInt(1, userid);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteBookOne(int bookid) {
        try {
            PreparedStatement pr = connection.prepareStatement("DELETE FROM books WHERE bookid = ?");
            pr.setInt(1, bookid);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        try {
            PreparedStatement pr = connection
                    .prepareStatement("UPDATE books SET author=?,title=?,genre=? WHERE bookid = ?");
            pr.setString(1, book.getAuthor());
            pr.setString(2, book.getTitle());
            pr.setString(3, book.getGenre());
            //pr.setInt(4, book.getUserid());
            pr.setInt(4, book.getBookId());
            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book getBookId(int bookid) {
        Book book = new Book();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM books WHERE bookid=?");
            ps.setInt(1, bookid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book.setBookId(rs.getInt("bookid"));
                book.setUserid(rs.getInt("userid"));
                book.setTitle(rs.getString("title"));
                book.setGenre(rs.getString("genre"));
                book.setAuthor(rs.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }


}
