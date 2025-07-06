import React, { createContext, useContext, useEffect, useState } from "react";

const PatientContext = createContext();
export const usePatients = () => useContext(PatientContext);

export const PatientProvider = ({ children }) => {
  const [patients, setPatients] = useState(() => {
    const saved = localStorage.getItem("patients");
    return saved ? JSON.parse(saved) : [];
  });

  useEffect(() => {
    localStorage.setItem("patients", JSON.stringify(patients));
  }, [patients]);

  return (
    <PatientContext.Provider value={{ patients, setPatients }}>
      {children}
    </PatientContext.Provider>
  );
};
