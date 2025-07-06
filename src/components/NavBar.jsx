import React from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const NavBar = () => {
  const { user, logout } = useAuth();

  const roleLinks = {
    admin: [
      { to: "/dashboard/admin", label: "Dashboard" },
      { to: "/patients", label: "Patients" },
      { to: "/appointments", label: "Appointments" },
      { to: "/billing", label: "Billing" },
      { to: "/inventory", label: "Inventory" },
    ],
    doctor: [
      { to: "/dashboard/doctor", label: "Dashboard" },
      { to: "/patients", label: "Patients" },
    ],
    pharmacist: [
      { to: "/dashboard/pharmacist", label: "Dashboard" },
      { to: "/inventory", label: "Inventory" },
    ],
    receptionist: [
      { to: "/dashboard/receptionist", label: "Dashboard" },
      { to: "/appointments", label: "Appointments" },
    ],
  };

  return (
    <nav className="bg-blue-600 text-white px-6 py-3 flex justify-between items-center shadow">
      <div className="font-bold text-xl">Washington Hospital</div>
      <div className="flex gap-4 items-center">
        {user &&
          roleLinks[user.role]?.map((link) => (
            <Link key={link.to} to={link.to} className="hover:underline">
              {link.label}
            </Link>
          ))}
        {user ? (
          <>
            <Link to="/profile" className="hover:underline">
              {user.username}
            </Link>
            <button onClick={logout} className="ml-2 bg-white text-blue-600 px-2 py-1 rounded">
              Logout
            </button>
          </>
        ) : (
          <>
            <Link to="/login" className="hover:underline">
              Login
            </Link>
            <Link to="/signup" className="hover:underline">
              Signup
            </Link>
          </>
        )}
      </div>
    </nav>
  );
};

export default NavBar;
