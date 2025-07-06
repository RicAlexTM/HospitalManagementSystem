import React, { useState, useEffect } from "react";
import { toast } from "react-toastify";
import { v4 as uuidv4 } from "uuid";

const Inventory = () => {
  const [items, setItems] = useState(() => {
    const stored = localStorage.getItem("inventory");
    return stored ? JSON.parse(stored) : [];
  });

  const [form, setForm] = useState({ name: "", quantity: "" });
  const [search, setSearch] = useState("");

  useEffect(() => {
    localStorage.setItem("inventory", JSON.stringify(items));
  }, [items]);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleAdd = (e) => {
    e.preventDefault();
    if (!form.name || !form.quantity) return toast.error("All fields required");
    setItems([...items, { id: uuidv4(), ...form }]);
    setForm({ name: "", quantity: "" });
    toast.success("Item added");
  };

  const handleDelete = (id) => {
    setItems(items.filter((i) => i.id !== id));
    toast.success("Item removed");
  };

  const filtered = items.filter((i) =>
    i.name.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold mb-4">Inventory</h2>

      <input
        placeholder="Search by name"
        className="mb-4 p-2 border w-full rounded"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      <form onSubmit={handleAdd} className="space-y-2 mb-6">
        <input
          name="name"
          placeholder="Item name"
          className="border p-2 w-full rounded"
          value={form.name}
          onChange={handleChange}
        />
        <input
          name="quantity"
          placeholder="Quantity"
          className="border p-2 w-full rounded"
          value={form.quantity}
          onChange={handleChange}
        />
        <button className="bg-blue-600 text-white px-4 py-2 rounded">Add Item</button>
      </form>

      <ul className="space-y-2">
        {filtered.map((i) => (
          <li key={i.id} className="border p-3 rounded flex justify-between">
            <div>
              <strong>{i.name}</strong> — {i.quantity}
            </div>
            <button
              onClick={() => handleDelete(i.id)}
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

export default Inventory;
