package com.hospital.services;

import com.hospital.models.Appointment;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Optional<Appointment> getAppointmentById(int id);
    boolean createAppointment(Appointment appointment);
    boolean updateAppointment(Appointment appointment);
    boolean deleteAppointment(int id);
    List<Appointment> getAppointmentsByPatient(int patientId);
    List<Appointment> getAppointmentsByDoctor(int doctorId);
    boolean updateAppointmentStatus(int id, String status);
}