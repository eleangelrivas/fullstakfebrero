import axios from "axios";

const API_SALES = "http://localhost:9191/api/v1/ventas";

// export const getSales_sinpaginacion = async () => {
//   const token = localStorage.getItem("token");
//   const response = await axios.get(API_SALES, {
//     headers: { Authorization: `Bearer ${token}` },
//   });
//   return response.data;
// };

export const getSales = async (page = 0, size = 10) => {
    console.log("page: ", page, " size:", size);
    const token = localStorage.getItem("token");
    const response = await axios.get(`http://localhost:9191/api/v1/ventas?p=${page}&limit=${size}`, {
        headers: { Authorization: `Bearer ${token}` },
    });
    console.log("despues de paginar:: ", response);
    return response.data;
};