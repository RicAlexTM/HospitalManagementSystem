import React from "react";
import { Link } from "react-router-dom";

const Landing = () => (
  <div className="flex flex-col items-center justify-center h-screen bg-cover bg-center bg-gray-100">
    <h1 className="text-4xl font-bold mb-6 text-blue-700">Welcome to Washington Hospital</h1>
    <p className="text-lg mb-8 text-gray-700">Your health, our priority</p>
    <div className="space-x-4">
      <Link to="/login" className="bg-blue-600 text-white px-6 py-3 rounded hover:bg-blue-700">
        Login
      </Link>
      <Link to="/signup" className="bg-green-600 text-white px-6 py-3 rounded hover:bg-green-700">
        Sign Up
      </Link>
    </div>
  </div>
);

export default Landing;
