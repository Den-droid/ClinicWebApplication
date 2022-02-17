package com.example.KursovaRobota.servlets;

import com.example.KursovaRobota.models.Doctor;
import com.example.KursovaRobota.services.IDoctorService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "home", value = "/")
public class HomeServlet extends HttpServlet {
    IDoctorService doctorService;

    @Override
    public void init(ServletConfig config) {
        doctorService = (IDoctorService) config.getServletContext().getAttribute("doctorService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            doctors = doctorService.getAll();
            if (doctors.isEmpty()) {
                request.setAttribute("error", "Докторів не знайдено");
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Сталася помилка. Спробуйте пізніше!!!");
        }
        request.setAttribute("title", "Головна");
        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request, response);
    }
}
