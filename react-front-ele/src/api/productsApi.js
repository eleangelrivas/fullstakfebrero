import axios from "axios";

const API_URL = "http://localhost:9191/api/v1/products";
const API_CATEGORY = "http://localhost:9191/api/v1/categories";

//nota en la api page la manejo como p y size como limit
//se habilito la paginacion 19 feb
export const getProducts = async (page = 0, size = 10) => {
  console.log("page: ", page, " size:", size);
  const token = localStorage.getItem("token");
  console.log("token en productos: ",token);
  const response = await axios.get(API_URL+`?p=${page}&limit=${size}`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  console.log("despues de paginar:: ", response);
  console.log("response: ",response);
  return response.data;
};
 
export const getCategories = async () => {
  const token = localStorage.getItem("token");
  const response = await axios.get(API_CATEGORY, {
    headers: { Authorization: `Bearer ${token}` },
  });
  console.log("response categories ",response);
  if (!response.data) {
    throw new Error("Error al obtener las categorías");
  }
  return response.data;
};

export const updateProduct = async (productId, data) => {
  try {
    const token = localStorage.getItem("token");
    console.log("token al update producto",token);
    console.log("data al update producto",data);
    const response = await fetch(`http://localhost:9191/api/v1/products/${productId}`, {
      method: "PUT", // Método HTTP PUT para actualizar
      headers: {
        "Content-Type": "application/json", // Indicar que el cuerpo es JSON
        "Authorization": `Bearer ${token}`
      },
      body: JSON.stringify(data), // Convertir los datos a JSON
    });
    console.log("response en update producto: ",response);
    if (!response.ok) {
      throw new Error("Error al actualizar el producto");
    }

    return response.json(); // Devolver el producto actualizado
  } catch (error) {
    console.error("Error en updateProduct:", error);
    throw error; // Relanzar el error para manejarlo en el componente
  }
};

export const createProduct = async (data) => {
  try {
    const token = localStorage.getItem("token");
    const response = await fetch("http://localhost:9191/api/v1/products", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(data),
    });
    if (!response.ok) {
      throw new Error("Error al crear el producto");
    }
    return response.json();
  } catch (error) {
    console.error("Error en createProduct:", error);
    throw error;
  }
};


export const deleteProduct = async (productId) => {
  try {
    const token = localStorage.getItem("token");

    const response = await fetch(`http://localhost:9191/api/v1/products/${productId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    });

    if (!response.ok) {
      throw new Error("Error al eliminar el producto");
    }

    // La api me devolvera algo como: { "id_eliminado": 1, "message": "Producto en proceso de eliminación" }
    const data = await response.json();
    return data; 
  } catch (error) {
    console.error("Error en deleteProduct:", error);
    throw error;
  }
};




export const getChartData = async () => {
  const token = localStorage.getItem("token");
  const response = await axios.get(`${API_CATEGORY}/obtenergrafico`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  console.log("response de grafico: ",response);
  return response.data;
};