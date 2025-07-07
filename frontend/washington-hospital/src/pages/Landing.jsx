import React from "react";
import { useNavigate } from "react-router-dom";

const Landing = () => {
  const navigate = useNavigate();

  return (
    <div className="container" style={{ textAlign: "center", maxWidth: "600px" }}>
      <div className="card">
        <h1>Welcome to Washington Hospital</h1>
        <p>Your trusted partner in healthcare innovation and patient care.</p>

        <div style={{ marginTop: "30px" }}>
          <button onClick={() => navigate("/login")} style={{ marginRight: "10px" }}>
            Login
          </button>
          <button onClick={() => navigate("/signup")}>
            Sign Up
          </button>
        </div>
      </div>
    </div>
  );
};

export default Landing;
