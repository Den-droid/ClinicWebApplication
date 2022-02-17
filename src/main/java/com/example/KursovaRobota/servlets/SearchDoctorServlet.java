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

@WebServlet(name = "searchDoctor", value = {"/search", "/admin/search"})
public class SearchDoctorServlet extends HttpServlet {
    IDoctorService doctorService;

    @Override
    public void init(ServletConfig config) {
        doctorService = (IDoctorService) config.getServletContext().getAttribute("doctorService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            doctors = doctorService.getByName(name);
            if (doctors.isEmpty()) {
                request.setAttribute("error", "Таких докторів не знайдено");
            }
        } catch (SQLException exception) {
            request.setAttribute("error", "Сталася помилка. Спробуйте пізніше!!!");
        }

        request.setAttribute("title", "Пошук доктора");
        request.setAttribute("doctors", doctors);
        if (request.getRequestURI().contains(request.getContextPath() + "/admin/search")) {
            request.getRequestDispatcher("/WEB-INF/pages/admin/admin.jsp").forward(request, response);
        } else if (request.getRequestURI().equals(request.getContextPath() + "/search")) {
            request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
        }
    }
}
