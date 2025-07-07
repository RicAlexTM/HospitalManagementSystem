import React, { useState, useEffect } from "react";
import "../index.css";
import { toast } from "react-toastify";

const AdminDashboard = () => {
  const [patients, setPatients] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [editedPatient, setEditedPatient] = useState({});

  useEffect(() => {
    const storedPatients = JSON.parse(localStorage.getItem("patients")) || [];
    setPatients(storedPatients);
  }, []);

  const handleDelete = (id) => {
    if (!window.confirm("Are you sure you want to delete this patient?")) return;
    const updated = patients.filter((p) => p.id !== id);
    setPatients(updated);
    localStorage.setItem("patients", JSON.stringify(updated));
    toast.success("Patient deleted.");
  };

  const handleEdit = (patient) => {
    setEditingId(patient.id);
    setEditedPatient({ ...patient });
  };

  const handleSave = () => {
    const updated = patients.map((p) =>
      p.id === editingId ? editedPatient : p
    );
    setPatients(updated);
    localStorage.setItem("patients", JSON.stringify(updated));
    setEditingId(null);
    toast.success("Patient updated.");
  };

  const handleChange = (e) => {
    setEditedPatient({ ...editedPatient, [e.target.name]: e.target.value });
  };

  const filteredPatients = patients.filter((p) =>
    p.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="page-container">
      <h2 className="page-title">Admin Dashboard</h2>
      <input
        type="text"
        placeholder="Search patients..."
        className="input search-input"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      <table className="styled-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Age</th>
            <th>Diagnosis</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredPatients.map((patient) => (
            <tr key={patient.id}>
              <td>
                {editingId === patient.id ? (
                  <input
                    name="name"
                    value={editedPatient.name}
                    onChange={handleChange}
                  />
                ) : (
                  patient.name
                )}
              </td>
              <td>
                {editingId === patient.id ? (
                  <input
                    name="age"
                    value={editedPatient.age}
                    onChange={handleChange}
                  />
                ) : (
                  patient.age
                )}
              </td>
              <td>
                {editingId === patient.id ? (
                  <input
                    name="diagnosis"
                    value={editedPatient.diagnosis}
                    onChange={handleChange}
                  />
                ) : (
                  patient.diagnosis
                )}
              </td>
              <td>
                {editingId === patient.id ? (
                  <>
                    <button className="btn save-btn" onClick={handleSave}>
                      Save
                    </button>
                    <button className="btn cancel-btn" onClick={() => setEditingId(null)}>
                      Cancel
                    </button>
                  </>
                ) : (
                  <>
                    <button className="btn edit-btn" onClick={() => handleEdit(patient)}>
                      Edit
                    </button>
                    <button className="btn delete-btn" onClick={() => handleDelete(patient.id)}>
                      Delete
                    </button>
                  </>
                )}
              </td>
            </tr>
          ))}
          {filteredPatients.length === 0 && (
            <tr>
              <td colSpan="4" className="text-center">
                No matching patients found.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default AdminDashboard;
