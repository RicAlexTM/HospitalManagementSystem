import React, { useState, useEffect } from "react";
import "../index.css";
import { toast } from "react-toastify";

const DoctorDashboard = () => {
  const [patients, setPatients] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [editedPatient, setEditedPatient] = useState({});

  useEffect(() => {
    const savedPatients = JSON.parse(localStorage.getItem("patients")) || [];
    setPatients(savedPatients);
  }, []);

  const handleDelete = (id) => {
    if (!window.confirm("Delete this patient?")) return;
    const updated = patients.filter((p) => p.id !== id);
    setPatients(updated);
    localStorage.setItem("patients", JSON.stringify(updated));
    toast.success("Patient removed.");
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

  const filtered = patients.filter((p) =>
    p.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="page-container">
      <h2 className="page-title">Doctor Dashboard</h2>
      <input
        className="input search-input"
        placeholder="Search patient..."
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
          {filtered.map((p) => (
            <tr key={p.id}>
              <td>
                {editingId === p.id ? (
                  <input
                    name="name"
                    value={editedPatient.name}
                    onChange={handleChange}
                  />
                ) : (
                  p.name
                )}
              </td>
              <td>
                {editingId === p.id ? (
                  <input
                    name="age"
                    value={editedPatient.age}
                    onChange={handleChange}
                  />
                ) : (
                  p.age
                )}
              </td>
              <td>
                {editingId === p.id ? (
                  <input
                    name="diagnosis"
                    value={editedPatient.diagnosis}
                    onChange={handleChange}
                  />
                ) : (
                  p.diagnosis
                )}
              </td>
              <td>
                {editingId === p.id ? (
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
                    <button className="btn edit-btn" onClick={() => handleEdit(p)}>
                      Edit
                    </button>
                    <button className="btn delete-btn" onClick={() => handleDelete(p.id)}>
                      Delete
                    </button>
                  </>
                )}
              </td>
            </tr>
          ))}
          {filtered.length === 0 && (
            <tr>
              <td colSpan="4" className="text-center">
                No results found.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default DoctorDashboard;
