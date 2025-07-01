package com.hospital.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hospital.models.MedicalHistory;
import com.hospital.utils.DatabaseUtil;

public class MedicalHistoryDAO {

    public static List<MedicalHistory> getAllMedicalHistories() {
        List<MedicalHistory> historyList = new ArrayList<>();
        String query = "SELECT * FROM medical_histories";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MedicalHistory history = new MedicalHistory(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null, // optional patient object
                    rs.getString("condition_name"),
                    rs.getDate("diagnosis_date"),
                    rs.getString("treatment"),
                    rs.getString("notes")
                );

                historyList.add(history);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyList;
    }

    public static List<MedicalHistory> getByPatientId(int patientId) {
        List<MedicalHistory> results = new ArrayList<>();
        String query = "SELECT * FROM medical_histories WHERE patient_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MedicalHistory history = new MedicalHistory(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null,
                    rs.getString("condition_name"),
                    rs.getDate("diagnosis_date"),
                    rs.getString("treatment"),
                    rs.getString("notes")
                );

                results.add(history);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public static boolean addMedicalHistory(MedicalHistory history) {
        String query = """
            INSERT INTO medical_histories (patient_id, condition_name, diagnosis_date, treatment, notes)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, history.getPatientId());
            stmt.setString(2, history.getConditionName());
            stmt.setDate(3, history.getDiagnosisDate());
            stmt.setString(4, history.getTreatment());
            stmt.setString(5, history.getNotes());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
