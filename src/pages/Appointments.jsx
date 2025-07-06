import React, { useState, useEffect } from "react";
import { v4 as uuidv4 } from "uuid";
import { toast } from "react-toastify";

const Appointments = () => {
  const [appointments, setAppointments] = useState(() => {
    const stored = localStorage.getItem("appointments");
    return stored ? JSON.parse(stored) : [];
  });

  const [form, setForm] = useState({ patient: "", date: "", doctor: "" });
  const [search, setSearch] = useState("");

  useEffect(() => {
    localStorage.setItem("appointments", JSON.stringify(appointments));
  }, [appointments]);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleAdd = (e) => {
    e.preventDefault();
    if (!form.patient || !form.date || !form.doctor)
      return toast.error("Fill all fields");
    setAppointments([...appointments, { id: uuidv4(), ...form }]);
    setForm({ patient: "", date: "", doctor: "" });
    toast.success("Appointment added");
  };

  const handleDelete = (id) => {
    setAppointments(appointments.filter((a) => a.id !== id));
    toast.success("Appointment deleted");
  };

  const filtered = appointments.filter((a) =>
    a.patient.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold mb-4">Appointments</h2>

      <input
        placeholder="Search by patient name"
        className="mb-4 p-2 border w-full rounded"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      <form onSubmit={handleAdd} className="space-y-2 mb-6">
        <input
          name="patient"
          placeholder="Patient"
          className="border p-2 w-full rounded"
          value={form.patient}
          onChange={handleChange}
        />
        <input
          name="date"
          type="date"
          className="border p-2 w-full rounded"
          value={form.date}
          onChange={handleChange}
        />
        <input
          name="doctor"
          placeholder="Doctor"
          className="border p-2 w-full rounded"
          value={form.doctor}
          onChange={handleChange}
        />
        <button className="bg-blue-600 text-white px-4 py-2 rounded">Add Appointment</button>
      </form>

      <ul className="space-y-2">
        {filtered.map((a) => (
          <li key={a.id} className="border p-3 rounded flex justify-between">
            <div>
              <strong>{a.patient}</strong> — {a.date} with Dr. {a.doctor}
            </div>
            <button
              onClick={() => handleDelete(a.id)}
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

export default Appointments;
