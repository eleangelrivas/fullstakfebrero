import { useEffect, useState } from "react";
import { getClients } from "../api/clientsApi";  
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import Navbar from "../components/Navbar";

import "primereact/resources/themes/saga-blue/theme.css";  
import "primereact/resources/primereact.min.css";  
import "primeicons/primeicons.css";  

const Clients = () => {
  const [clients, setClients] = useState([]);  
  const [totalRecords, setTotalRecords] = useState(0);  
  const [loading, setLoading] = useState(false);  
  const [page, setPage] = useState(0);  
  const [size, setSize] = useState(10);  

  // obtener los clientes al cargar o al navegar
  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const clientsData = await getClients(page, size);  
        setClients(clientsData.content);
        setTotalRecords(clientsData.totalElements);  
      } catch (error) {
        console.error("Error al obtener los clientes:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [page, size]); // Ojo, pagina y limit (p,limit)

  // Actualizacion de cambio de page
  const onPageChange = (event) => {
    setPage(event.page); 
    setSize(event.rows); 
  };

  return (
    <div>
      <Navbar />
      <h1>Clientes</h1>


      <DataTable
        value={clients}
        paginator
        rows={size}
        totalRecords={totalRecords}
        lazy
        loading={loading}
        onPage={onPageChange}
        rowsPerPageOptions={[5, 10, 20]}
      >
        <Column field="id" header="ID" sortable />
        <Column field="nombre" header="Nombre" sortable />
        <Column field="correo" header="Correo" sortable />
      </DataTable>
    </div>
  );
};

export default Clients;