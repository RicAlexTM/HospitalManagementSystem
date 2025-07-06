import React, { useState } from "react";
import { useAuth } from "../context/AuthContext";

const DoctorDashboard = () => {
  const { user } = useAuth();
  const [patients, setPatients] = useState([
    { id: 1, name: "John Doe", condition: "Flu" },
    { id: 2, name: "Jane Smith", condition: "Migraine" }
  ]);
  const [search, setSearch] = useState("");

  const filtered = patients.filter(p => p.name.toLowerCase().includes(search.toLowerCase()));

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold mb-4">Doctor Dashboard</h1>
      <p className="mb-4">Welcome, Dr. {user?.username}</p>

      <input
        type="text"
        placeholder="Search patients..."
        className="border p-2 mb-4 w-full"
        value={search}
        onChange={e => setSearch(e.target.value)}
      />

      <ul className="space-y-2">
        {filtered.map(p => (
          <li key={p.id} className="bg-gray-100 p-2 rounded">
            <strong>{p.name}</strong> - {p.condition}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default DoctorDashboard;
