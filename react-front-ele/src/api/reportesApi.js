import axios from "axios";
//debido a que jpa no nos dejo crear como Object hubo necesidad de usar una entidad propia mapeada para 
//poder reducir tiempo de desarrollo
const API_BASE_URL = "http://localhost:9191/api/v1/categories";

export const getTopProducts = async () => {
  const token = localStorage.getItem("token");
  const response = await axios.get(`${API_BASE_URL}/productostop3`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

export const getTopClients = async () => {
  const token = localStorage.getItem("token");
  const response = await axios.get(`${API_BASE_URL}/clientestop3`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

export const getIncomeByMonth = async () => {
  const token = localStorage.getItem("token");
  const response = await axios.get(`${API_BASE_URL}/ingresosxmes`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};
