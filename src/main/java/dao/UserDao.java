package dao;

import modele.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pc on 30.07.2017.
 */
public class UserDao {
    private static Connection connection;
    private BookDao bookDao;

    public UserDao() {
        this.bookDao = new BookDao();
        connection = DBUtil.getConnection();
    }

    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users4(firstname, lastname,city,tel, dob, email) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setInt(4, user.getTel());
            preparedStatement.setDate(5, new Date(user.getDob().getTime()));
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userid) {
    bookDao.deleteBook(userid);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users4 WHERE userid = ?");
            preparedStatement.setInt(1, userid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users4 SET firstname=?, lastname=?,city=?,tel=?, dob=?, email=? WHERE userid=?");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setInt(4, user.getTel());
            preparedStatement.setDate(5, new Date(user.getDob().getTime()));
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setInt(7, user.getUserid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users4 = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users4");
            while (rs.next()) {
                User user = new User();
                user.setUserid(rs.getInt("userid"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setCity(rs.getString("city"));
                user.setTel(rs.getInt("tel"));
                user.setEmail(rs.getString("email"));
                user.setDob(rs.getDate("dob"));
                users4.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users4;
    }

    public User getUserById(int userid) {
        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users4 WHERE userid=?");
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setUserid(rs.getInt("userid"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setCity(rs.getString("city"));
                user.setTel(rs.getInt("tel"));
                user.setEmail(rs.getString("email"));
                user.setDob(rs.getDate("dob"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
