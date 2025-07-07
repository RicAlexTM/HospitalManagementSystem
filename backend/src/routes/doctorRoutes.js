import express from 'express';
import {
  getDoctorProfile,
  updateDoctorInfo,
  listDoctorAppointments,
  viewAssignedPatients
} from '../controllers/doctorController.js';
import auth from '../middleware/authMiddleware.js';

const router = express.Router();

// ✅ Temporary test route to verify routing works
router.get('/', (req, res) => {
  res.send('Doctor route works!');
});

// 🩺 Protected routes (require auth)
router.get('/:id/profile', auth(['Doctor']), getDoctorProfile);
router.put('/:id', auth(['Doctor']), updateDoctorInfo);
router.get('/:id/appointments', auth(['Doctor']), listDoctorAppointments);
router.get('/:id/patients', auth(['Doctor']), viewAssignedPatients);

export default router;
