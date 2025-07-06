import React, { createContext, useContext, useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";

// Create the context
const AuthContext = createContext();

// Custom hook to use auth
export const useAuth = () => useContext(AuthContext);

// Provider component
export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem("user");
    return saved ? JSON.parse(saved) : null;
  });

  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    if (!user && location.pathname !== "/login" && location.pathname !== "/signup") {
      navigate("/login");
    }
  }, [user, navigate, location.pathname]);

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
