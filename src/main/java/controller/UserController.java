package controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final UserService userService = new UserService();

    @GetMapping("/list")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        return "add";
    }

    @PostMapping("/add")
    public ModelAndView postAdd(String id, String name, String country, String email) {
        System.out.println(id);
        System.out.println(name);
        System.out.println(country);
        System.out.println(email);
        int id_ = Integer.parseInt(id);
        userService.add(new User(id_, name, country, email));

        return new ModelAndView("redirect:list");
    }

    @GetMapping("/update")
    public ModelAndView update(String id, String name, String country, String email) {
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("id", id);
        modelAndView.addObject("name", name);
        modelAndView.addObject("country", country);
        modelAndView.addObject("email", email);
        return modelAndView;
    }

    @PostMapping("update")
    public ModelAndView postUpdate(String id, String name, String country, String email) {
        userService.update(Integer.parseInt(id), new User(Integer.parseInt(id), name, country, email));
        return new ModelAndView("redirect:list");
    }

    @GetMapping("remove")
    public ModelAndView remove(String id) {
        int id_ = Integer.parseInt(id);
        userService.remove(id_);
        return new ModelAndView("redirect:list");
    }
    /*private void removeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
    }*/
}
