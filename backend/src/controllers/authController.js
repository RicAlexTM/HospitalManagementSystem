import pool from '../db.js';
import bcrypt from 'bcrypt';
import { generateToken } from '../utils/jwt.js';

export async function register(req, res) {
  const { name, email, password, role } = req.body;
  if (!['Patient', 'Doctor', 'Admin'].includes(role)) {
    return res.status(400).json({ message: 'Invalid role' });
  }
  const hashed = await bcrypt.hash(password, 10);
  try {
    const result = await pool.query(
      'INSERT INTO users (name, email, password, role) VALUES ($1, $2, $3, $4) RETURNING id, name, email, role',
      [name, email, hashed, role]
    );
    const user = result.rows[0];
    const token = generateToken(user);
    res.status(201).json({ user, token });
  } catch (err) {
    res.status(400).json({ message: 'Registration failed', error: err.message });
  }
}