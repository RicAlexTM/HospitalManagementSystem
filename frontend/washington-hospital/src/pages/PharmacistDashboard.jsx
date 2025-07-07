import React, { useEffect, useState } from "react";
import "../index.css";
import { toast } from "react-toastify";

const PharmacistDashboard = () => {
  const [inventory, setInventory] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [editedItem, setEditedItem] = useState({});

  useEffect(() => {
    const stored = JSON.parse(localStorage.getItem("inventory")) || [];
    setInventory(stored);
  }, []);

  const handleEdit = (item) => {
    setEditingId(item.id);
    setEditedItem({ ...item });
  };

  const handleChange = (e) => {
    setEditedItem({ ...editedItem, [e.target.name]: e.target.value });
  };

  const handleSave = () => {
    if (!editedItem.name || !editedItem.quantity || !editedItem.expiry) {
      toast.error("All fields are required");
      return;
    }

    const updated = inventory.map((item) =>
      item.id === editingId ? editedItem : item
    );
    setInventory(updated);
    localStorage.setItem("inventory", JSON.stringify(updated));
    toast.success("Item updated!");
    setEditingId(null);
  };

  const handleDelete = (id) => {
    if (!window.confirm("Delete this item?")) return;
    const updated = inventory.filter((item) => item.id !== id);
    setInventory(updated);
    localStorage.setItem("inventory", JSON.stringify(updated));
    toast.success("Item deleted!");
  };

  const filtered = inventory.filter((item) =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="page-container">
      <h2 className="page-title">Pharmacist Dashboard</h2>

      <input
        className="input search-input"
        placeholder="Search medicine..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />

      <table className="styled-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Qty</th>
            <th>Expiry</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filtered.map((item) => (
            <tr key={item.id}>
              <td>
                {editingId === item.id ? (
                  <input
                    name="name"
                    value={editedItem.name}
                    onChange={handleChange}
                  />
                ) : (
                  item.name
                )}
              </td>
              <td>
                {editingId === item.id ? (
                  <input
                    name="quantity"
                    type="number"
                    value={editedItem.quantity}
                    onChange={handleChange}
                  />
                ) : (
                  item.quantity
                )}
              </td>
              <td>
                {editingId === item.id ? (
                  <input
                    name="expiry"
                    type="date"
                    value={editedItem.expiry}
                    onChange={handleChange}
                  />
                ) : (
                  item.expiry
                )}
              </td>
              <td>
                {editingId === item.id ? (
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
                    <button className="btn edit-btn" onClick={() => handleEdit(item)}>
                      Edit
                    </button>
                    <button className="btn delete-btn" onClick={() => handleDelete(item.id)}>
                      Delete
                    </button>
                  </>
                )}
              </td>
            </tr>
          ))}
          {filtered.length === 0 && (
            <tr>
              <td colSpan="4" className="text-center">No items found.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default PharmacistDashboard;
