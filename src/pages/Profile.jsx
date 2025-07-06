import React, { useState } from "react";
import { useAuth } from "../context/AuthContext";

const Profile = () => {
  const { user } = useAuth();
  const [username, setUsername] = useState(user?.username || "");

  const handleUpdate = () => {
    alert("Profile updated (mock)");
  };

  return (
    <div className="max-w-xl mx-auto bg-white p-6 rounded shadow mt-10">
      <h2 className="text-2xl font-bold mb-4">My Profile</h2>
      <div className="mb-4">
        <label className="block mb-1">Username</label>
        <input
          className="border p-2 w-full rounded"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </div>
      <div className="mb-4">
        <label className="block mb-1">Role</label>
        <input className="border p-2 w-full rounded bg-gray-100" value={user?.role} readOnly />
      </div>
      <button onClick={handleUpdate} className="bg-blue-600 text-white px-4 py-2 rounded">
        Update
      </button>
    </div>
  );
};

export default Profile;
