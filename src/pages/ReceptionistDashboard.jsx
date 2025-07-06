import React, { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";
import { toast } from "react-toastify";

const ReceptionistDashboard = () => {
  const { user } = useAuth();
  const [appointments, setAppointments] = useState(() => {
    const saved = localStorage.getItem("appointments");
    return saved ? JSON.parse(saved) : [
      { id: 1, name: "John Doe", date: "2025-07-10", reason: "Consultation" },
      { id: 2, name: "Jane Smith", date: "2025-07-11", reason: "Check-up" }
    ];
  });
  const [form, setForm] = useState({ name: "", date: "", reason: "" });
  const [search, setSearch] = useState("");

  useEffect(() => {
    localStorage.setItem("appointments", JSON.stringify(appointments));
  }, [appointments]);

  const handleChange = e =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = e => {
    e.preventDefault();
    const newAppointment = { ...form, id: Date.now() };
    setAppointments(prev => [...prev, newAppointment]);
    setForm({ name: "", date: "", reason: "" });
    toast.success("Appointment added successfully!");
  };

  const handleDelete = id => {
    setAppointments(prev => prev.filter(app => app.id !== id));
    toast.info("Appointment removed.");
  };

  const filtered = appointments.filter(app =>
    app.name.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="p-6 max-w-3xl mx-auto">
      <h1 className="text-3xl font-bold mb-4">Receptionist Dashboard</h1>
      <p className="mb-4 text-gray-700">Hi {user?.username}, manage appointments below.</p>

      {/* Search Bar */}
      <input
        type="text"
        placeholder="Search by name..."
        className="border p-2 mb-4 w-full"
        value={search}
        onChange={e => setSearch(e.target.value)}
      />

      {/* Form */}
      <form onSubmit={handleSubmit} className="bg-white p-4 shadow rounded space-y-4 mb-6">
        <input
          type="text"
          name="name"
          placeholder="Patient Name"
          value={form.name}
          onChange={handleChange}
          required
          className="border p-2 w-full"
        />
        <input
          type="date"
          name="date"
          value={form.date}
          onChange={handleChange}
          required
          className="border p-2 w-full"
        />
        <input
          type="text"
          name="reason"
          placeholder="Reason for Visit"
          value={form.reason}
          onChange={handleChange}
          required
          className="border p-2 w-full"
        />
        <button className="bg-blue-600 text-white px-4 py-2 rounded">Add Appointment</button>
      </form>

      {/* Appointment List */}
      <ul className="space-y-2">
        {filtered.map(app => (
          <li key={app.id} className="bg-gray-100 p-3 rounded flex justify-between items-center">
            <div>
              <strong>{app.name}</strong> — {app.date} — {app.reason}
            </div>
            <button
              onClick={() => handleDelete(app.id)}
              className="text-red-600 hover:underline"
            >
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ReceptionistDashboard;
