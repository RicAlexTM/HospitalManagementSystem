import express from 'express';
import { bookAppointment } from '../controllers/appointmentController.js';
import auth from '../middleware/authMiddleware.js';

const router = express.Router();
router.post('/', auth(['Patient']), bookAppointment);

export default router;