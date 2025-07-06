import React, { useState, useEffect } from "react";
import { v4 as uuidv4 } from "uuid";
import { toast } from "react-toastify";

const Billing = () => {
  const [bills, setBills] = useState(() => {
    const stored = localStorage.getItem("bills");
    return stored ? JSON.parse(stored) : [];
  });

  const [form, setForm] = useState({ patient: "", amount: "", description: "" });
  const [search, setSearch] = useState("");

  useEffect(() => {
    localStorage.setItem("bills", JSON.stringify(bills));
  }, [bills]);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleAdd = (e) => {
    e.preventDefault();
    if (!form.patient || !form.amount)
      return toast.error("Patient and amount are required");
    setBills([...bills, { id: uuidv4(), ...form }]);
    setForm({ patient: "", amount: "", description: "" });
    toast.success("Bill added");
  };

  const handleDelete = (id) => {
    setBills(bills.filter((b) => b.id !== id));
    toast.success("Bill removed");
  };

  const filtered = bills.filter((b) =>
    b.patient.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold mb-4">Billing</h2>

      <input
        placeholder="Search by patient"
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
          name="amount"
          placeholder="Amount"
          className="border p-2 w-full rounded"
          value={form.amount}
          onChange={handleChange}
        />
        <input
          name="description"
          placeholder="Description"
          className="border p-2 w-full rounded"
          value={form.description}
          onChange={handleChange}
        />
        <button className="bg-blue-600 text-white px-4 py-2 rounded">Add Bill</button>
      </form>

      <ul className="space-y-2">
        {filtered.map((b) => (
          <li key={b.id} className="border p-3 rounded flex justify-between">
            <div>
              <strong>{b.patient}</strong> — ${b.amount} ({b.description})
            </div>
            <button
              onClick={() => handleDelete(b.id)}
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

export default Billing;
