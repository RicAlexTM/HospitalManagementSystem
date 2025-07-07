import React, { useState } from 'react';
import '../index.css';

const Inventory = () => {
  const [items, setItems] = useState([]);
  const [newItem, setNewItem] = useState({ name: '', quantity: '' });

  const addItem = () => {
    if (newItem.name && newItem.quantity) {
      setItems([...items, { ...newItem, id: Date.now() }]);
      setNewItem({ name: '', quantity: '' });
    }
  };

  const deleteItem = (id) => {
    setItems(items.filter(i => i.id !== id));
  };

  return (
    <div className="container">
      <h2>Inventory</h2>
      <input
        type="text"
        placeholder="Item Name"
        value={newItem.name}
        onChange={(e) => setNewItem({ ...newItem, name: e.target.value })}
      />
      <input
        type="number"
        placeholder="Quantity"
        value={newItem.quantity}
        onChange={(e) => setNewItem({ ...newItem, quantity: e.target.value })}
      />
      <button onClick={addItem}>Add Item</button>
      <ul>
        {items.map(i => (
          <li key={i.id}>
            {i.name} - {i.quantity}
            <button onClick={() => deleteItem(i.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Inventory;
