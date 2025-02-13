import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2"; // Importamos SweetAlert2
import { login } from "../api/authApi";

const Login = () => {
  const [username, setUsuario] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const result = await login({ username, password });

    if (result.success) {
      console.log("Token recibido:", result.token);

   
      Swal.fire({
        icon: "success",
        title: "¡Inicio de sesión exitoso!",
        text: "Redirigiéndote al dashboard...",
        timer: 2000, 
        showConfirmButton: false,
      });

      setTimeout(() => {
        navigate("/dashboard");
      }, 2000);  
    } else {
      console.error("Error de autenticación:", result.error);
 
      Swal.fire({
        icon: "error",
        title: "Error al iniciar sesión",
        text: result.error || "Verifica tus credenciales",
      });
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Usuario"
        onChange={(e) => setUsuario(e.target.value)}
      />
      <input
        type="password"
        placeholder="Contraseña"
        onChange={(e) => setPassword(e.target.value)}
      />
      <button type="submit">Iniciar sesión</button>
    </form>
  );
};

export default Login;
