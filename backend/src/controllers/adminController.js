import pool from '../db.js';

export async function createUser(req, res) {
  const { name, email, password, role } = req.body;
  try {
    const result = await pool.query(
      'INSERT INTO users (name, email, password, role) VALUES ($1, $2, $3, $4) RETURNING *',
      [name, email, password, role]
    );
    res.status(201).json(result.rows[0]);
  } catch (err) {
    res.status(400).json({ message: 'User creation failed', error: err.message });
  }
}

export async function updateUser(req, res) {
  const userId = req.params.id;
  const { name, email } = req.body;
  try {
    const result = await pool.query('UPDATE users SET name = $1, email = $2 WHERE id = $3 RETURNING *', [name, email, userId]);
    res.json(result.rows[0]);
  } catch (err) {
    res.status(400).json({ message: 'User update failed', error: err.message });
  }
}

export async function deleteUser(req, res) {
  const userId = req.params.id;
  try {
    await pool.query('DELETE FROM users WHERE id = $1', [userId]);
    res.json({ message: 'User deleted' });
  } catch (err) {
    res.status(400).json({ message: 'Delete failed', error: err.message });
  }
}

export async function viewStatistics(req, res) {
  try {
    const result = await pool.query('SELECT COUNT(*) FROM users');
    res.json({ totalUsers: result.rows[0].count });
  } catch (err) {
    res.status(400).json({ message: 'Stats error', error: err.message });
  }
}

export async function assignRole(req, res) {
  const { userId, role } = req.body;
  try {
    const result = await pool.query('UPDATE users SET role = $1 WHERE id = $2 RETURNING *', [role, userId]);
    res.json(result.rows[0]);
  } catch (err) {
    res.status(400).json({ message: 'Role assignment failed', error: err.message });
  }
}

export async function viewAllAppointments(req, res) {
  try {
    const result = await pool.query('SELECT * FROM appointments');
    res.json(result.rows);
  } catch (err) {
    res.status(400).json({ message: 'Error retrieving appointments', error: err.message });
  }
}
