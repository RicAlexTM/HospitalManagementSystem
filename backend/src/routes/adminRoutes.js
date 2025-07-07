import express from 'express';
import {
  createUser,
  updateUser,
  deleteUser,
  viewStatistics,
  assignRole,
  viewAllAppointments
} from '../controllers/adminController.js';
import auth from '../middleware/authMiddleware.js';

const router = express.Router();

router.post('/users', auth(['Admin']), createUser);
router.put('/users/:id', auth(['Admin']), updateUser);
router.delete('/users/:id', auth(['Admin']), deleteUser);
router.get('/stats', auth(['Admin']), viewStatistics);
router.post('/assign-role', auth(['Admin']), assignRole);
router.get('/appointments', auth(['Admin']), viewAllAppointments);

export default router;
