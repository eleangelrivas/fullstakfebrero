import React, { useEffect, useState } from "react";
import { Bar } from "react-chartjs-2";
import { getTopProducts } from "../api/reportesApi";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

const Productostop3 = () => {
  const [chartData, setChartData] = useState({
    labels: [],
    datasets: [
      {
        label: "Productos más vendidos",
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
        const data = await getTopProducts(); 
        const labels = data.map(([id, name, count]) => name); 
        const counts = data.map(([id, name, count]) => count); 

        setChartData({
          labels: labels,
          datasets: [
            {
              ...chartData.datasets[0],
              data: counts,
            },
          ],
        });
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
      <h2>Productos más vendidos</h2>
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

export default Productostop3;