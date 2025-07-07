// src/context/AuthContext.jsx
import React, { createContext, useContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem("user");
    return saved ? JSON.parse(saved) : null;
  });

  useEffect(() => {
    if (!user) {
      const stored = localStorage.getItem("user");
      if (!stored) {
        navigate("/login");
      }
    }
  }, [user, navigate]);

  const login = ({ username, password, role }) => {
    const fakeUser = { username, role };
    localStorage.setItem("user", JSON.stringify(fakeUser));
    setUser(fakeUser);
    navigate(`/dashboard/${role}`);
  };

  const logout = () => {
    localStorage.removeItem("user");
    setUser(null);
    navigate("/login");
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
