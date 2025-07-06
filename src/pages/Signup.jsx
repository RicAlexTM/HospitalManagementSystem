import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const Signup = () => {
  const [form, setForm] = useState({
    username: "",
    password: "",
    confirmPassword: "",
    role: "admin",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const isStrongPassword = (password) => {
    const strongRegex =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;
    return strongRegex.test(password);
  };

  const handleSignup = (e) => {
    e.preventDefault();

    if (form.password !== form.confirmPassword) {
      toast.error("❌ Passwords do not match");
      return;
    }

    if (!isStrongPassword(form.password)) {
      toast.error(
        "⚠️ Password must be at least 8 characters, include uppercase, lowercase, number, and symbol."
      );
      return;
    }

    toast.success("✅ Signup successful (mock)");
    navigate("/login");
  };

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <form
        onSubmit={handleSignup}
        className="bg-white p-6 rounded-xl shadow-md space-y-4 w-96"
      >
        <h2 className="text-2xl font-bold text-center">Sign Up</h2>

        <input
          name="username"
          placeholder="Username"
          className="border p-2 w-full rounded"
          value={form.username}
          onChange={handleChange}
          required
        />

        <input
          type="password"
          name="password"
          placeholder="Password"
          className="border p-2 w-full rounded"
          value={form.password}
          onChange={handleChange}
          required
        />

        <input
          type="password"
          name="confirmPassword"
          placeholder="Confirm Password"
          className="border p-2 w-full rounded"
          value={form.confirmPassword}
          onChange={handleChange}
          required
        />

        <select
          name="role"
          value={form.role}
          onChange={handleChange}
          className="border p-2 w-full rounded"
        >
          <option value="admin">Admin</option>
          <option value="doctor">Doctor</option>
          <option value="pharmacist">Pharmacist</option>
          <option value="receptionist">Receptionist</option>
        </select>

        <button
          type="submit"
          className="w-full bg-green-600 hover:bg-green-700 text-white p-2 rounded"
        >
          Sign Up
        </button>
      </form>
    </div>
  );
};

export default Signup;
