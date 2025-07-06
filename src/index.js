// src/index.js
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './index.css';
import { BrowserRouter } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { AuthProvider } from './context/AuthContext';
import { PatientProvider } from './context/PatientContext';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <React.StrictMode>
    <BrowserRouter>
      <AuthProvider>
        <PatientProvider>
          <App />
          <ToastContainer position="top-right" autoClose={3000} />
        </PatientProvider>
      </AuthProvider>
    </BrowserRouter>
  </React.StrictMode>
);
