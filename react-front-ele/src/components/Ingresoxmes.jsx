import React, { useEffect, useState } from "react";
import { Bar } from "react-chartjs-2";
import { getIncomeByMonth } from "../api/reportesApi";
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

const Ingresoxmes = () => {
  const [chartData, setChartData] = useState({
    labels: [],
    datasets: [
      {
        label: "Ingresos por mes",
        data: [],
        backgroundColor: "rgba(153, 102, 255, 0.2)",
        borderColor: "rgba(153, 102, 255, 1)",
        borderWidth: 1,
      },
    ],
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getIncomeByMonth(); 
        const labels = data.map(([month, income]) => month); 
        const incomes = data.map(([month, income]) => income); 

        setChartData({
          labels: labels,
          datasets: [
            {
              ...chartData.datasets[0],
              data: incomes,
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
      <h2>Ingresos por mes</h2>
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

export default Ingresoxmes;