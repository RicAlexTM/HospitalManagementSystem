package com.hospital.services.impl;

import com.hospital.models.Appointment;
import com.hospital.daos.AppointmentDAO;
import com.hospital.daos.impl.AppointmentDAOImpl;
import com.hospital.services.AppointmentService;

import java.util.List;
import java.util.Optional;

public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentDAO.findAll();
    }
    
    @Override
    public Optional<Appointment> getAppointmentById(int id) {
        return appointmentDAO.findById(id);
    }

    @Override
    public boolean createAppointment(Appointment appointment) {
        return appointmentDAO.create(appointment);
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        return appointmentDAO.update(appointment);
    }

    @Override
    public boolean deleteAppointment(int id) {
        return appointmentDAO.delete(id);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(int patientId) {
        return appointmentDAO.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        return appointmentDAO.findByDoctorId(doctorId);
    }

    @Override
    public boolean updateAppointmentStatus(int id, String status) {
        return appointmentDAO.updateStatus(id, status);
    }
}