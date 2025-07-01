package com.hospital.services;

import java.util.List;

import com.hospital.daos.AppointmentDAO;
import com.hospital.models.Appointment;

public class AppointmentService {

    public List<Appointment> getAllAppointments() {
        return AppointmentDAO.getAllAppointments();
    }

    public Appointment getAppointmentById(int id) {
        return AppointmentDAO.getAppointmentById(id);
    }

    public boolean scheduleAppointment(Appointment appointment) {
        // You could optionally add checks like:
        // - is doctor available?
        // - does patient exist?
        // - is appointment date valid?
        

        return AppointmentDAO.createAppointment(appointment);
    }

    public boolean updateStatus(int appointmentId, String newStatus) {
        return AppointmentDAO.updateAppointmentStatus(appointmentId, newStatus);
    }
}
