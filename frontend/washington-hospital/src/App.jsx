// src/App.jsx
import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import NavBar from "./components/NavBar";

// Pages
import Landing from "./pages/Landing";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Profile from "./pages/Profile";
import Patients from "./pages/Patients";
import Billing from "./pages/Billing";
import Inventory from "./pages/Inventory";
import Appointments from "./pages/Appointments";
import Unauthorized from "./pages/Unauthorized";

// Role-protected logic
import ProtectedRoute from "./pages/ProtectedRoute";
import RoleRoute from "./pages/RoleRoute";

// Dashboards
import AdminDashboard from "./pages/AdminDashboard";
import DoctorDashboard from "./pages/DoctorDashboard";
import PharmacistDashboard from "./pages/PharmacistDashboard";
import ReceptionistDashboard from "./pages/ReceptionistDashboard";

const App = () => {
  return (
    <>
      <NavBar />
      <div className="container">
        <Routes>
          <Route path="/" element={<Landing />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/unauthorized" element={<Unauthorized />} />

          <Route
            path="/profile"
            element={
              <ProtectedRoute>
                <Profile />
              </ProtectedRoute>
            }
          />

          {/* Role-based Dashboards */}
          <Route
            path="/dashboard/admin"
            element={
              <ProtectedRoute>
                <RoleRoute allowed={["admin"]}>
                  <AdminDashboard />
                </RoleRoute>
              </ProtectedRoute>
            }
          />
          <Route
            path="/dashboard/doctor"
            element={
              <ProtectedRoute>
                <RoleRoute allowed={["doctor"]}>
                  <DoctorDashboard />
                </RoleRoute>
              </ProtectedRoute>
            }
          />
          <Route
            path="/dashboard/pharmacist"
            element={
              <ProtectedRoute>
                <RoleRoute allowed={["pharmacist"]}>
                  <PharmacistDashboard />
                </RoleRoute>
              </ProtectedRoute>
            }
          />
          <Route
            path="/dashboard/receptionist"
            element={
              <ProtectedRoute>
                <RoleRoute allowed={["receptionist"]}>
                  <ReceptionistDashboard />
                </RoleRoute>
              </ProtectedRoute>
            }
          />

          {/* Feature Pages */}
          <Route
            path="/patients"
            element={
              <ProtectedRoute>
                <RoleRoute allowed={["admin", "doctor"]}>
                  <Patients />
                </RoleRoute>
              </ProtectedRoute>
            }
          />
          <Route
            path="/appointments"
            element={
              <ProtectedRoute>
                <RoleRoute allowed={["admin", "receptionist"]}>
                  <Appointments />
                </RoleRoute>
              </ProtectedRoute>
            }
          />
          <Route
            path="/billing"
            element={
              <ProtectedRoute>
                <RoleRoute allowed={["admin"]}>
                  <Billing />
                </RoleRoute>
              </ProtectedRoute>
            }
          />
          <Route
            path="/inventory"
            element={
              <ProtectedRoute>
                <RoleRoute allowed={["admin", "pharmacist"]}>
                  <Inventory />
                </RoleRoute>
              </ProtectedRoute>
            }
          />

          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </div>
    </>
  );
};

export default App;
