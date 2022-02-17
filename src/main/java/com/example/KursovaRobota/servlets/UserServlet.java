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

@WebServlet(name = "user", value = "/user")
public class UserServlet extends HttpServlet {
    IUserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (IUserService) config.getServletContext().getAttribute("userService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            appointments = userService.getAppointments(user.getId());
            if (appointments.isEmpty()) {
                request.setAttribute("error", "Ви не записані до жодного доктора!");
            }
        } catch (SQLException exception) {
            request.setAttribute("error", "Сталася помилка. Спробуйте пізніше!!!");
        }
        request.setAttribute("title", "Сторінка користувача");
        request.setAttribute("timetable", appointments);
        request.getRequestDispatcher("/WEB-INF/pages/user/user.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        User user = (User) request.getSession().getAttribute("user");
        try {
            userService.deleteAppointment(appointmentId, user.getId());
            request.setAttribute("message", "Ви успішно відписалися від цього часу!");
        } catch (SQLException exception) {
            request.setAttribute("error", "Сталася помилка. Спробуйте пізніше!!!");
        }
        doGet(request, response);
    }
}
