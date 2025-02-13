import axios from "axios";

const API_CLIENTS = "http://localhost:9191/api/v1/clientes";

export const getClients = async (page = 0, size = 10) => {
    const token = localStorage.getItem("token");
    const response = await axios.get(`${API_CLIENTS}?p=${page}&limit=${size}`, {
        headers: { Authorization: `Bearer ${token}` },
    });
    return response.data;
};