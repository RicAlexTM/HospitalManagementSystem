import React from "react";
import { Link } from "react-router-dom";

const Unauthorized = () => {
  return (
    <div className="flex flex-col items-center justify-center h-screen bg-gray-50 text-center">
      <h1 className="text-4xl font-bold text-red-600 mb-4">Unauthorized</h1>
      <p className="mb-6 text-gray-700">You do not have access to this page.</p>
      <Link to="/" className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
        Go Home
      </Link>
    </div>
  );
};

export default Unauthorized;
