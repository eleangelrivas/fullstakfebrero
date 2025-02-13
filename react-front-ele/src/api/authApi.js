import { getRoleFromToken } from "../utils/authUtils";
import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:9191/api/v1/auth",
    withCredentials: true,
    headers: { "Content-Type": "application/json" }
});

export const login = async (credentials) => {
    try {
        const response = await api.post("/authenticate", credentials);

        if (response.data.jwt) {
            const token = response.data.jwt;
            console.log("Token JWT:", token);

             
            const role = getRoleFromToken(token);
            console.log("Rol del usuario:", role);

            
            localStorage.setItem("token", token);
            localStorage.setItem("role", role);

            return { success: true, token, role };
        } else {
            return { success: false, error: "Token no recibido" };
        }
    } catch (error) {
        console.error("Error en la autenticación:", error);
        return { success: false, error: error.response?.data?.message || "Error al iniciar sesión" };
    }
};
