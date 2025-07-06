import React from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const RoleRoute = ({ allowed, children }) => {
  const { user } = useAuth();
  if (!user || !allowed.includes(user.role)) return <Navigate to="/unauthorized" />;
  return children;
};

export default RoleRoute;
