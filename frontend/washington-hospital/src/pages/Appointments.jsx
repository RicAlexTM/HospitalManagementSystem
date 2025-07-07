import React, { useState } from 'react';
import '../index.css';

const Appointments = () => {
  const [appointments, setAppointments] = useState([]);
  const [form, setForm] = useState({ patient: '', date: '' });

  const addAppointment = () => {
    if (form.patient && form.date) {
      setAppointments([...appointments, { ...form, id: Date.now() }]);
      setForm({ patient: '', date: '' });
    }
  };

  const deleteAppointment = (id) => {
    setAppointments(appointments.filter(a => a.id !== id));
  };

  return (
    <div className="container">
      <h2>Appointments</h2>
      <input
        type="text"
        placeholder="Patient Name"
        value={form.patient}
        onChange={(e) => setForm({ ...form, patient: e.target.value })}
      />
      <input
        type="date"
        value={form.date}
        onChange={(e) => setForm({ ...form, date: e.target.value })}
      />
      <button onClick={addAppointment}>Add</button>
      <ul>
        {appointments.map(a => (
          <li key={a.id}>
            {a.patient} - {a.date}
            <button onClick={() => deleteAppointment(a.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Appointments;
