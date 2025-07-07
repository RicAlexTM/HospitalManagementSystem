import React, { useState } from 'react';
import '../index.css';

const Billing = () => {
  const [bills, setBills] = useState([]);
  const [newBill, setNewBill] = useState({ patient: '', amount: '' });

  const addBill = () => {
    if (newBill.patient && newBill.amount) {
      setBills([...bills, { ...newBill, id: Date.now() }]);
      setNewBill({ patient: '', amount: '' });
    }
  };

  const deleteBill = (id) => {
    setBills(bills.filter(b => b.id !== id));
  };

  return (
    <div className="container">
      <h2>Billing</h2>
      <input
        type="text"
        placeholder="Patient Name"
        value={newBill.patient}
        onChange={(e) => setNewBill({ ...newBill, patient: e.target.value })}
      />
      <input
        type="number"
        placeholder="Amount"
        value={newBill.amount}
        onChange={(e) => setNewBill({ ...newBill, amount: e.target.value })}
      />
      <button onClick={addBill}>Add Bill</button>
      <ul>
        {bills.map(b => (
          <li key={b.id}>
            {b.patient}: ${b.amount}
            <button onClick={() => deleteBill(b.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Billing;
