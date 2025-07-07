import pool from '../db.js';

export async function getDoctorProfile(req, res) {
  const doctorId = req.params.id;
  try {
    const result = await pool.query('SELECT * FROM users WHERE id = $1 AND role = $2', [doctorId, 'Doctor']);
    res.json(result.rows[0]);
  } catch (err) {
    res.status(400).json({ message: 'Failed to get doctor profile', error: err.message });
  }
}

export async function updateDoctorInfo(req, res) {
  const doctorId = req.params.id;
  const { name, email } = req.body;
  try {
    const result = await pool.query('UPDATE users SET name = $1, email = $2 WHERE id = $3 RETURNING *', [name, email, doctorId]);
    res.json(result.rows[0]);
  } catch (err) {
    res.status(400).json({ message: 'Update failed', error: err.message });
  }
}

export async function listDoctorAppointments(req, res) {
  const doctorId = req.params.id;
  try {
    const result = await pool.query('SELECT * FROM appointments WHERE doctor_id = $1', [doctorId]);
    res.json(result.rows);
  } catch (err) {
    res.status(400).json({ message: 'Failed to get appointments', error: err.message });
  }
}

export async function viewAssignedPatients(req, res) {
  const doctorId = req.params.id;
  try {
    const result = await pool.query('SELECT DISTINCT p.* FROM patients p JOIN appointments a ON p.id = a.patient_id WHERE a.doctor_id = $1', [doctorId]);
    res.json(result.rows);
  } catch (err) {
    res.status(400).json({ message: 'Failed to get patients', error: err.message });
  }
}
