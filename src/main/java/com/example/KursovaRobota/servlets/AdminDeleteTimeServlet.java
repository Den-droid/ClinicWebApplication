package com.example.KursovaRobota.servlets;

import com.example.KursovaRobota.models.Appointment;
import com.example.KursovaRobota.services.ITimetableService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "deleteTime", value = "/admin/delete_time")
public class AdminDeleteTimeServlet extends HttpServlet {
    ITimetableService timetableService;

    @Override
    public void init(ServletConfig config) {
        timetableService = (ITimetableService) config.getServletContext().getAttribute("timetableService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            appointments = timetableService.getByDoctorsId(id);
            if (appointments.isEmpty()) {
                request.setAttribute("error", "Розкладу не знайдено");
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Сталася помилка. Спробуйте пізніше!!!");
        }

        request.setAttribute("title", "Видалити часи прийому");
        request.setAttribute("timetable", appointments);
        request.getRequestDispatcher("/WEB-INF/pages/admin/deleteHours.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        try {
            timetableService.delete(appointmentId);
        } catch (SQLException exception) {
            request.setAttribute("error", "Сталася помилка. Спробуйте пізніше!!!");
        }
        doGet(request, response);
    }
}
