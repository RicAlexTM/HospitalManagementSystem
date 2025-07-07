import React, { createContext, useContext, useState } from "react";
import { v4 as uuidv4 } from "uuid";

const PatientContext = createContext();

export const usePatient = () => useContext(PatientContext);

export const PatientProvider = ({ children }) => {
  const [patients, setPatients] = useState([]);

  const addPatient = patient => {
    setPatients(prev => [...prev, { ...patient, id: uuidv4() }]);
  };

  const updatePatient = (id, updated) => {
    setPatients(prev =>
      prev.map(p => (p.id === id ? { ...p, ...updated } : p))
    );
  };

  const deletePatient = id => {
    setPatients(prev => prev.filter(p => p.id !== id));
  };

  return (
    <PatientContext.Provider value={{ patients, addPatient, updatePatient, deletePatient }}>
      {children}
    </PatientContext.Provider>
  );
};
