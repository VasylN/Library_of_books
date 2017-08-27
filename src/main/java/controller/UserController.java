package controller;

import dao.UserDao;
import modele.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pc on 09.07.2017.
 */
public class UserController extends HttpServlet {
    private UserDao dao;
    private static String INSERT_OR_EDIT = "/updateUser.jsp";
    private static String LIST_USER = "/listUser.jsp";


    public UserController() {
        this.dao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int userid = Integer.parseInt(req.getParameter("userid"));
            dao.deleteUser(userid);
            forward = LIST_USER;
            req.setAttribute("users", dao.getAllUsers());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int userid = Integer.parseInt(req.getParameter("userid"));
            User user = dao.getUserById(userid);
            req.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")) {
            forward = LIST_USER;
            req.setAttribute("users", dao.getAllUsers());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher rd = req.getRequestDispatcher(forward);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setCity(req.getParameter("city"));
        user.setTel(req.getIntHeader("tel"));
        try {
            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("dob"));
            user.setDob(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user.setEmail(req.getParameter("email"));
        String userid = req.getParameter("userid");

        if (userid == null || userid.isEmpty()) {
            dao.addUser(user);
        } else {
            user.setUserid(Integer.parseInt(userid));
            dao.updateUser(user);
        }

        RequestDispatcher rd = req.getRequestDispatcher(LIST_USER);
        req.setAttribute("users", dao.getAllUsers());
        rd.forward(req, resp);

    }
}
