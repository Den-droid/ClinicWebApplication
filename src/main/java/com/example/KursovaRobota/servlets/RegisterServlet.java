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

@WebServlet(name = "registration", value = "/register")
public class RegisterServlet extends HttpServlet {
    IUserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (IUserService) config.getServletContext().getAttribute("userService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Реєстрація");
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("email");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String type = "Customer";
        try {
            User user = userService.register(new User(login, fullname, type, password));
            if (user != null) {
                response.sendRedirect("/login");
            } else {
                request.setAttribute("error", "Такий користувач вже існує");
                doGet(request, response);
            }
        } catch (SQLException exception) {
            request.setAttribute("error", "Сталася помилка. Спробуйте пізніше!!!");
            doGet(request, response);
        }
    }
}