package com.example.KursovaRobota.servlets;

import com.example.KursovaRobota.models.User;
import com.example.KursovaRobota.services.IUserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    IUserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (IUserService) config.getServletContext().getAttribute("userService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Увійти");
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        String login = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User user = userService.login(new User(login, null, null, password));
            if (user == null) {
                request.setAttribute("error", "Невірний логін/пароль");
                doGet(request, response);
            } else {
                request.getSession().setAttribute("user", user);
                if (user.getType().equals("Admin")) {
                    request.getSession().setAttribute("urlProfile", "admin");
                } else if (user.getType().equals("Customer")) {
                    request.getSession().setAttribute("urlProfile", "user");
                }
                response.sendRedirect("/");
            }
        } catch (SQLException ex) {
            request.setAttribute("error", "Сталася помилка. Спробуйте пізніше!!!");
            doGet(request, response);
        }
    }
}
