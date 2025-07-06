import React, { useState, useEffect } from "react";
import { toast } from "react-toastify";
import { v4 as uuidv4 } from "uuid";

const Patients = () => {
  const [patients, setPatients] = useState(() => {
    const stored = localStorage.getItem("patients");
    return stored ? JSON.parse(stored) : [];
  });

  const [form, setForm] = useState({ name: "", age: "", condition: "" });
  const [search, setSearch] = useState("");

  useEffect(() => {
    localStorage.setItem("patients", JSON.stringify(patients));
  }, [patients]);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleAdd = (e) => {
    e.preventDefault();
    if (!form.name || !form.age) return toast.error("All fields required");
    setPatients([...patients, { id: uuidv4(), ...form }]);
    toast.success("Patient added");
    setForm({ name: "", age: "", condition: "" });
  };

  const handleDelete = (id) => {
    setPatients(patients.filter((p) => p.id !== id));
    toast.success("Patient removed");
  };

  const filtered = patients.filter((p) =>
    p.name.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold mb-4">Patients</h2>

      <input
        type="text"
        placeholder="Search by name..."
        className="mb-4 p-2 border w-full rounded"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      <form onSubmit={handleAdd} className="space-y-2 mb-6">
        <input
          name="name"
          value={form.name}
          onChange={handleChange}
          placeholder="Name"
          className="border p-2 w-full rounded"
        />
        <input
          name="age"
          value={form.age}
          onChange={handleChange}
          placeholder="Age"
          className="border p-2 w-full rounded"
        />
        <input
          name="condition"
          value={form.condition}
          onChange={handleChange}
          placeholder="Condition"
          className="border p-2 w-full rounded"
        />
        <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded">
          Add Patient
        </button>
      </form>

      <ul className="space-y-2">
        {filtered.map((p) => (
          <li key={p.id} className="border p-3 rounded flex justify-between">
            <div>
              <strong>{p.name}</strong> (Age: {p.age}) — {p.condition}
            </div>
            <button
              onClick={() => handleDelete(p.id)}
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

export default Patients;
