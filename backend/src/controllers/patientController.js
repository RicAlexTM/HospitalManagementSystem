import pool from '../db.js';

export async function getMedicalRecords(req, res) {
  const patientId = req.params.id;
  try {
    const result = await pool.query(
      'SELECT * FROM medical_records WHERE patient_id = $1',
      [patientId]
    );
    res.json(result.rows);
  } catch (err) {
    res.status(400).json({ message: 'Error fetching records', error: err.message });
  }
}