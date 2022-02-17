package com.example.KursovaRobota.dao.implement;

import com.example.KursovaRobota.dao.IDaoFactory;
import com.example.KursovaRobota.dao.IDoctorDao;
import com.example.KursovaRobota.dao.ITimetableDao;
import com.example.KursovaRobota.dao.IUserDao;

public class DaoFactory implements IDaoFactory {
    private final IDoctorDao doctorDao;
    private final IUserDao userDao;
    private final ITimetableDao timetableDao;

    public DaoFactory(IDoctorDao iDoctorDao, IUserDao iUserDao, ITimetableDao iTimetableDao) throws ClassNotFoundException {
        this.doctorDao = iDoctorDao;
        this.userDao = iUserDao;
        this.timetableDao = iTimetableDao;
        this.loadDriver();
    }

    @Override
    public IDoctorDao getDoctorDao() {
        return doctorDao;
    }

    @Override
    public ITimetableDao getTimetableDao() {
        return timetableDao;
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }
}
