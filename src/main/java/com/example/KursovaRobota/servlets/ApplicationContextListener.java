package com.example.KursovaRobota.servlets;

import com.example.KursovaRobota.dao.IDaoFactory;
import com.example.KursovaRobota.dao.IDoctorDao;
import com.example.KursovaRobota.dao.ITimetableDao;
import com.example.KursovaRobota.dao.IUserDao;
import com.example.KursovaRobota.dao.implement.DaoFactory;
import com.example.KursovaRobota.dao.implement.DoctorDao;
import com.example.KursovaRobota.dao.implement.TimetableDao;
import com.example.KursovaRobota.dao.implement.UserDao;
import com.example.KursovaRobota.services.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        IDoctorDao doctorDao = new DoctorDao();
        ITimetableDao timetableDao = new TimetableDao();
        IUserDao userDao = new UserDao();

        try {
            IDaoFactory iDaoFactory = new DaoFactory(doctorDao, userDao, timetableDao);
            IDoctorService doctorService = new DoctorService(iDaoFactory);
            IUserService userService = new UserService(iDaoFactory);
            ITimetableService timetableService = new TimetableService(iDaoFactory);
            sce.getServletContext().setAttribute("doctorService", doctorService);
            sce.getServletContext().setAttribute("timetableService", timetableService);
            sce.getServletContext().setAttribute("userService", userService);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ApplicationContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
