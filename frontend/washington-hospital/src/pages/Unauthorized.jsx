import React from 'react';
import { Link } from 'react-router-dom';
import '../index.css';

const Unauthorized = () => (
  <div className="container">
    <h2>Unauthorized Access</h2>
    <p>You do not have permission to view this page.</p>
    <Link to="/">Return to Home</Link>
  </div>
);

export default Unauthorized;
