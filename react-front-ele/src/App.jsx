import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import Products from "./pages/Products";
import Clients from "./pages/Clients";
import { AuthProvider } from "./context/AuthContext";
import Sales from "./pages/Sales";
import Resumen from "./pages/Resumen";

import './App.css'
const App = () => {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/products" element={<Products />} />
          <Route path="/ventas" element={<Sales />} />
          <Route path="/clientes" element={<Clients />} />
          <Route path="/resumen" element={<Resumen />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;
