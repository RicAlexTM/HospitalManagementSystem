import React from 'react';
import { useAuth } from '../context/AuthContext';
import '../index.css';

const Profile = () => {
  const { user, logout } = useAuth();

  return (
    <div className="container">
      <h2>User Profile</h2>
      {user ? (
        <div className="card">
          <p><strong>Username:</strong> {user.username}</p>
          <p><strong>Role:</strong> {user.role}</p>
          <button onClick={logout}>Logout</button>
        </div>
      ) : (
        <p>No user is logged in.</p>
      )}
    </div>
  );
};

export default Profile;
