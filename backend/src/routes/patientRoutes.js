import express from 'express';
import { getMedicalRecords } from '../controllers/patientController.js';
import auth from '../middleware/authMiddleware.js';

const router = express.Router();
router.get('/:id/records', auth(['Patient', 'Doctor', 'Admin']), getMedicalRecords);

export default router;