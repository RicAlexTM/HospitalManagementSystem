import pool from '../db.js';

export async function bookAppointment(req, res) {
  const { patientId, doctorId, date } = req.body;
  // Add validation as needed
  try {
    const result = await pool.query(
      'INSERT INTO appointments (patient_id, doctor_id, date, status) VALUES ($1, $2, $3, $4) RETURNING *',
      [patientId, doctorId, date, 'Scheduled']
    );
    res.status(201).json(result.rows[0]);
  } catch (err) {
    res.status(400).json({ message: 'Booking failed', error: err.message });
  }
}