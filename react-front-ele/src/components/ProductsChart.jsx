import React, { useEffect, useState } from "react";
import { Bar } from "react-chartjs-2";
import { getChartData } from "../api/productsApi";
import {
  Chart as ChartJS,  
  CategoryScale,  
  LinearScale,  
  BarElement,  
  Title,
  Tooltip,
  Legend,
} from "chart.js";

 
ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const ProductsChart = () => {
  const [chartData, setChartData] = useState({
    labels: [],  
    datasets: [
      {
        label: "Productos por categoría",
        data: [],  
        backgroundColor: "rgba(75, 192, 192, 0.2)",
        borderColor: "rgba(75, 192, 192, 1)",
        borderWidth: 1,
      },
    ],
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getChartData();  
        const labels = data.map(([count, id, category]) => category);  
        const counts = data.map(([count]) => count);  

        setChartData((prevState) => ({
          ...prevState,
          labels: labels,
          datasets: [
            {
              ...prevState.datasets[0],
              data: counts,
            },
          ],
        }));
      } catch (error) {
        console.error("Error al obtener los datos del gráfico:", error);
        setError("Error al cargar los datos del gráfico");
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) {
    return <div>Cargando...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>Productos por categoría</h2>
      <Bar
        data={chartData}
        options={{
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        }}
      />
    </div>
  );
};

export default ProductsChart;