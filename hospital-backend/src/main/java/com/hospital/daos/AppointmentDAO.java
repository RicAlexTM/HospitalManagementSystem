package com.hospital.daos;

import com.hospital.models.Appointment;
import java.util.List;

public interface AppointmentDAO extends CrudDAO<Appointment, Integer> {
    List<Appointment> findByPatientId(int patientId);
    List<Appointment> findByDoctorId(int doctorId);
    boolean updateStatus(int appointmentId, String status);
}