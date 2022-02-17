package com.example.KursovaRobota.servlets;

import com.example.KursovaRobota.models.Appointment;
import com.example.KursovaRobota.models.User;
import com.example.KursovaRobota.services.ITimetableService;
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

@WebServlet(name = "timetable", value = "/timetable")
public class TimetableServlet extends HttpServlet {
    ITimetableService timetableService;
    IUserService userService;

    @Override
    public void init(ServletConfig config) {
        timetableService = (ITimetableService) config.getServletContext().getAttribute("timetableService");
        userService = (IUserService) config.getServletContext().getAttribute("userService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            appointments = timetableService.getByDoctorsId(id);
            if (appointments.isEmpty()) {
                request.setAttribute("error", "Розкладу не знайдено");
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Сталася помилка. Спробуйте пізніше!!!");
        }

        request.setAttribute("title", "Розклад доктора");
        request.setAttribute("timetable", appointments);
        request.getRequestDispatcher("/WEB-INF/pages/timetable.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && user.getType().equals("Customer")) {
            try {
                int res = userService.addAppointment(user.getId(), appointmentId);
                if (res == -1) {
                    request.setAttribute("message", "Ви вже записалися на цей прийом!");
                } else if (res == -2) {
                    request.setAttribute("message", "Місць більше немає!!!");
                } else {
                    request.setAttribute("message", "Ви успішно записалися!");
                }
            } catch (SQLException exception) {
                request.setAttribute("error", "Сталася помилка спробуйте пізніше");
            }
        } else {
            request.setAttribute("message", "Увійдіть як користувач, щоб записатися");
        }
        doGet(request, response);
    }
}
