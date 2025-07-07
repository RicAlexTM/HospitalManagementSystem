// src/components/NavBar.jsx
import React from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const NavBar = () => {
  const { user, logout } = useAuth();

  return (
    <nav>
      <Link to="/">Home</Link>
      {!user && (
        <>
          <Link to="/login">Login</Link>
          <Link to="/signup">Signup</Link>
        </>
      )}
      {user && (
        <>
          <Link to="/profile">Profile</Link>
          <button onClick={logout} style={{ marginLeft: "10px" }}>Logout</button>
        </>
      )}
    </nav>
  );
};

export default NavBar;
