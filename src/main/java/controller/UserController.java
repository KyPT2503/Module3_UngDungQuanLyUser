package controller;

import model.User;
import service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/users")
public class UserController extends HttpServlet {
    private static final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        if (action.equals("")) {
            showAll(request, response);
        } else if (action.equals("update")) {
            dispatcherUpdateRequest(request, response);
        } else if (action.equals("remove")) {
            removeUser(request, response);
        }
    }

    private void removeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isRemoved = userService.remove(Integer.parseInt(request.getParameter("id")));
        if (isRemoved) {
            response.sendRedirect("/users");
        } else {
            response.sendRedirect("/error-404.jsp");
        }
    }

    private void dispatcherUpdateRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String email = request.getParameter("email");
        request.setAttribute("user", new User(id, name, country, email));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/update.jsp");
        dispatcher.forward(request, response);
    }

    private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", userService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        if (action.equals("add")) {
            addUser(request, response);
        } else if (action.equals("update")) {
            updateUser(request, response);
        } else if (action.equals("find")) {
            findUser(request, response);
        }
    }

    private void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String find_by = request.getParameter("find_by");
        String value = request.getParameter("value");
        List<User> users;
        if (find_by.equals("name")) {
            users = userService.findByName(value);
        } else {
            users = userService.findByCountry(value);
        }
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/found.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String country = request.getParameter("country");
            String email = request.getParameter("email");
            boolean isAdded = userService.update(id, new User(id, name, country, email));
            if (!isAdded) throw new Exception();
            response.sendRedirect("/users");
        } catch (Exception e) {
            response.sendRedirect("/error-404.jsp");
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String country = request.getParameter("country");
            String email = request.getParameter("email");
            boolean isAdded = userService.add(new User(id, name, country, email));
            if (!isAdded) throw new Exception();
            response.sendRedirect("/users");
        } catch (Exception e) {
            response.sendRedirect("/error-404.jsp");
        }
    }
}
