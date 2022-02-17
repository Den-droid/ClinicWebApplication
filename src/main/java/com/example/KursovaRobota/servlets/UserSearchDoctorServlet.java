package com.example.KursovaRobota.servlets;

import com.example.KursovaRobota.models.Appointment;
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
import java.util.ArrayList;

@WebServlet(name="userSearchDoctor", value = "/user/search")
public class UserSearchDoctorServlet extends HttpServlet {
    IUserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (IUserService) config.getServletContext().getAttribute("userService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        User user = (User) request.getSession().getAttribute("user");
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            appointments = userService.getAppointmentsByDoctorName(user.getId(), name);
            if (appointments.isEmpty()) {
                request.setAttribute("error", "Докторів не знайдено");
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Сталася помилка. Спробуйте пізніше!!!");
        }
        request.setAttribute("title", "Пошук доктора");
        request.setAttribute("timetable", appointments);
        request.getRequestDispatcher("/WEB-INF/pages/user/user.jsp").forward(request, response);
    }
}
