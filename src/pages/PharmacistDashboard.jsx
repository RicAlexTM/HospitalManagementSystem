import React, { useState } from "react";
import { useAuth } from "../context/AuthContext";

const PharmacistDashboard = () => {
  const { user } = useAuth();
  const [inventory, setInventory] = useState([
    { id: 1, name: "Paracetamol", quantity: 100 },
    { id: 2, name: "Ibuprofen", quantity: 60 }
  ]);
  const [search, setSearch] = useState("");

  const filtered = inventory.filter(i => i.name.toLowerCase().includes(search.toLowerCase()));

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold mb-4">Pharmacist Dashboard</h1>
      <p className="mb-4">Hello {user?.username}, manage inventory below.</p>

      <input
        type="text"
        placeholder="Search inventory..."
        className="border p-2 mb-4 w-full"
        value={search}
        onChange={e => setSearch(e.target.value)}
      />

      <ul className="space-y-2">
        {filtered.map(item => (
          <li key={item.id} className="bg-gray-100 p-2 rounded">
            <strong>{item.name}</strong> - {item.quantity} units
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PharmacistDashboard;
