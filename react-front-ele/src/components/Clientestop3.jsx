import React, { useEffect, useState } from "react";
import { Pie } from "react-chartjs-2";
import { getTopClients } from "../api/reportesApi";

import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(ArcElement, Tooltip, Legend);

const Clientestop3 = () => {
  const [chartData, setChartData] = useState({
    labels: [],
    datasets: [
      {
        label: "Clientes que más han gastado",
        data: [],
        backgroundColor: [
          "rgba(255, 99, 132, 0.2)",
          "rgba(54, 162, 235, 0.2)",
          "rgba(255, 206, 86, 0.2)",
        ],
        borderColor: [
          "rgba(255, 99, 132, 1)",
          "rgba(54, 162, 235, 1)",
          "rgba(255, 206, 86, 1)",
        ],
        borderWidth: 1,
      },
    ],
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getTopClients(); 
        const labels = data.map(([id, name, total]) => name); 
        const totals = data.map(([id, name, total]) => total); 

        setChartData({
          labels: labels,
          datasets: [
            {
              ...chartData.datasets[0],
              data: totals,
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
      <h2>Clientes que más han gastado</h2>
      <Pie data={chartData} />
    </div>
  );
};

export default Clientestop3;