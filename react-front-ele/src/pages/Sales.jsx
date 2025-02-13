import { useEffect, useState } from "react";
import { getSales } from "../api/salesApi";  
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { Dialog } from "primereact/dialog";
import Navbar from "../components/Navbar";
import React from "react";

import "primereact/resources/themes/saga-blue/theme.css";  
import "primereact/resources/primereact.min.css";  
import "primeicons/primeicons.css";  

const Sales = () => {
  const [sales, setSales] = useState([]);  
  const [selectedSale, setSelectedSale] = useState(null);  
  const [displayDetailsDialog, setDisplayDetailsDialog] = useState(false);  
  const [totalRecords, setTotalRecords] = useState(0);  
  const [loading, setLoading] = useState(false);  
  const [page, setPage] = useState(0);  
  const [size, setSize] = useState(10);  

  
  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const salesData = await getSales(page, size); 
        setSales(salesData.content);
        setTotalRecords(salesData.totalElements);  
      } catch (error) {
        console.error("Error al obtener las ventas:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [page, size]);  

   
  const viewTemplate = (rowData) => {
    return (
      <Button
        label="Ver"
        icon="pi pi-eye"
        className="p-button-rounded p-button-info"
        onClick={() => {
          setSelectedSale(rowData);  
          setDisplayDetailsDialog(true);  
        }}
      />
    );
  };

   
  const clienteTemplate = (rowData) => {
    return <span>{rowData.cliente.nombre}</span>;
  };

   
  const onPageChange = (event) => {
    setPage(event.page);  
    setSize(event.rows);  
  };

  return (
    <div>
      <Navbar />
      <h1>Ventas</h1>

       
      <DataTable
        value={sales}
        paginator
        rows={size}
        totalRecords={totalRecords}
        lazy
        loading={loading}
        onPage={onPageChange}
        rowsPerPageOptions={[5, 10, 20]}
      >
        <Column field="id" header="ID" sortable />
        <Column field="fecha" header="Fecha" sortable />
        <Column header="Cliente" body={clienteTemplate} sortable />
        <Column header="Acciones" body={viewTemplate} />
      </DataTable>

   
      <Dialog
        header={`Detalles de la Venta #${selectedSale?.id}`}
        visible={displayDetailsDialog}
        onHide={() => setDisplayDetailsDialog(false)}
        style={{ width: "50vw" }}
      >
        {selectedSale && (
          <div>
            <h3>Productos adquiridos:</h3>
            <ul>
              {selectedSale.detalles.map((detalle) => (
                <li key={detalle.id}>
                  <strong>{detalle.producto.name}</strong> - Cantidad: {detalle.cantidad}, Total: ${detalle.total}
                </li>
              ))}
            </ul>
          </div>
        )}
      </Dialog>
    </div>
  );
};

export default Sales;