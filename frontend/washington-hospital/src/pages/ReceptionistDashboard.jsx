import React, { useEffect, useState } from "react";
import "../index.css";
import { toast } from "react-toastify";

const ReceptionistDashboard = () => {
  const [appointments, setAppointments] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [edited, setEdited] = useState({});

  useEffect(() => {
    const stored = JSON.parse(localStorage.getItem("appointments")) || [];
    setAppointments(stored);
  }, []);

  const handleEdit = (appt) => {
    setEditingId(appt.id);
    setEdited({ ...appt });
  };

  const handleChange = (e) => {
    setEdited({ ...edited, [e.target.name]: e.target.value });
  };

  const handleSave = () => {
    const updated = appointments.map((a) =>
      a.id === editingId ? edited : a
    );
    setAppointments(updated);
    localStorage.setItem("appointments", JSON.stringify(updated));
    toast.success("Appointment updated!");
    setEditingId(null);
  };

  const handleDelete = (id) => {
    if (!window.confirm("Delete this appointment?")) return;
    const updated = appointments.filter((a) => a.id !== id);
    setAppointments(updated);
    localStorage.setItem("appointments", JSON.stringify(updated));
    toast.success("Appointment deleted!");
  };

  const filtered = appointments.filter((a) =>
    a.patient.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="page-container">
      <h2 className="page-title">Receptionist Dashboard</h2>

      <input
        className="input search-input"
        placeholder="Search by patient..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />

      <table className="styled-table">
        <thead>
          <tr>
            <th>Patient</th>
            <th>Date</th>
            <th>Time</th>
            <th>Doctor</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filtered.map((appt) => (
            <tr key={appt.id}>
              <td>
                {editingId === appt.id ? (
                  <input
                    name="patient"
                    value={edited.patient}
                    onChange={handleChange}
                  />
                ) : (
                  appt.patient
                )}
              </td>
              <td>
                {editingId === appt.id ? (
                  <input
                    name="date"
                    type="date"
                    value={edited.date}
                    onChange={handleChange}
                  />
                ) : (
                  appt.date
                )}
              </td>
              <td>
                {editingId === appt.id ? (
                  <input
                    name="time"
                    type="time"
                    value={edited.time}
                    onChange={handleChange}
                  />
                ) : (
                  appt.time
                )}
              </td>
              <td>
                {editingId === appt.id ? (
                  <input
                    name="doctor"
                    value={edited.doctor}
                    onChange={handleChange}
                  />
                ) : (
                  appt.doctor
                )}
              </td>
              <td>
                {editingId === appt.id ? (
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
                    <button className="btn edit-btn" onClick={() => handleEdit(appt)}>
                      Edit
                    </button>
                    <button className="btn delete-btn" onClick={() => handleDelete(appt.id)}>
                      Delete
                    </button>
                  </>
                )}
              </td>
            </tr>
          ))}
          {filtered.length === 0 && (
            <tr>
              <td colSpan="5" className="text-center">No appointments found.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ReceptionistDashboard;
