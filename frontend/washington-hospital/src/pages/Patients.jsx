import React, { useState } from "react";
import { usePatient } from "../context/PatientContext";
import { toast } from "react-toastify";

const Patients = () => {
  const { patients, addPatient, updatePatient, deletePatient } = usePatient();
  const [search, setSearch] = useState("");
  const [editingPatient, setEditingPatient] = useState(null);
  const [formData, setFormData] = useState({ name: "", age: "", condition: "" });

  const filteredPatients = patients.filter(p =>
    p.name.toLowerCase().includes(search.toLowerCase())
  );

  const handleInputChange = e =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const handleSubmit = e => {
    e.preventDefault();
    if (!formData.name || !formData.age || !formData.condition) {
      toast.error("Please fill all fields");
      return;
    }

    if (editingPatient) {
      updatePatient(editingPatient.id, formData);
      toast.success("Patient updated");
    } else {
      addPatient(formData);
      toast.success("Patient added");
    }

    setFormData({ name: "", age: "", condition: "" });
    setEditingPatient(null);
  };

  const handleEdit = patient => {
    setEditingPatient(patient);
    setFormData({ name: patient.name, age: patient.age, condition: patient.condition });
  };

  const handleDelete = id => {
    if (window.confirm("Are you sure you want to delete this patient?")) {
      deletePatient(id);
      toast.info("Patient deleted");
    }
  };

  return (
    <div className="page-container">
      <h2 className="title">Patients Dashboard</h2>

      <input
        type="text"
        placeholder="Search by name..."
        className="input search-input"
        value={search}
        onChange={e => setSearch(e.target.value)}
      />

      <form onSubmit={handleSubmit} className="form">
        <h3>{editingPatient ? "Edit Patient" : "Add New Patient"}</h3>
        <input
          className="input"
          name="name"
          placeholder="Name"
          value={formData.name}
          onChange={handleInputChange}
        />
        <input
          className="input"
          name="age"
          placeholder="Age"
          value={formData.age}
          onChange={handleInputChange}
        />
        <input
          className="input"
          name="condition"
          placeholder="Condition"
          value={formData.condition}
          onChange={handleInputChange}
        />
        <button className="btn primary" type="submit">
          {editingPatient ? "Update" : "Add"}
        </button>
        {editingPatient && (
          <button
            className="btn cancel"
            type="button"
            onClick={() => {
              setEditingPatient(null);
              setFormData({ name: "", age: "", condition: "" });
            }}
          >
            Cancel
          </button>
        )}
      </form>

      <table className="data-table">
        <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Age</th>
            <th>Condition</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredPatients.length ? (
            filteredPatients.map((p, i) => (
              <tr key={p.id}>
                <td>{i + 1}</td>
                <td>{p.name}</td>
                <td>{p.age}</td>
                <td>{p.condition}</td>
                <td>
                  <button className="btn edit" onClick={() => handleEdit(p)}>
                    Edit
                  </button>
                  <button className="btn delete" onClick={() => handleDelete(p.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No patients found.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default Patients;
