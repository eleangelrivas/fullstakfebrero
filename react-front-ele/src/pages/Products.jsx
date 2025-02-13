import { useEffect, useState } from "react";
import { getProducts, updateProduct, getCategories,createProduct } from "../api/productsApi";  
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { Dialog } from "primereact/dialog";
import { InputText } from "primereact/inputtext";
import { Dropdown } from "primereact/dropdown";
import Navbar from "../components/Navbar";


import "primereact/resources/themes/saga-blue/theme.css";  
import "primereact/resources/primereact.min.css";  
import "primeicons/primeicons.css";  

const Products = () => {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);  
  const [selectedProduct, setSelectedProduct] = useState(null);  
  const [displayEditDialog, setDisplayEditDialog] = useState(false);  
  const [formData, setFormData] = useState({ name: "", price: 0, status: "ENABLED", categoryId: null });  

 
  useEffect(() => {
    const fetchData = async () => {
      const productsData = await getProducts();
      setProducts(productsData.content);

      const categoriesData = await getCategories();
      console.error("categorias obtenidas",categoriesData);
      setCategories(categoriesData.content);  
    };
    fetchData();
  }, []);

 
  const handleEdit = (product) => {
    setSelectedProduct(product);  
    setFormData({
      name: product.name,
      price: product.price,
      status: product.status,
      categoryId: product.category.id,  
    });
    setDisplayEditDialog(true);  
  };

   
  const handleDelete = (product) => {
    console.log("Eliminar producto:", product);
     
  };

 
  const handleSubmit1 = async () => {
    try {
       
      const dataToSend = {
        name: formData.name,
        price: formData.price,
        status: formData.status,
        categoryId: formData.categoryId,  
      };
  
       
      const updatedProduct = await updateProduct(selectedProduct.id, dataToSend);
      console.log("Producto actualizado:", updatedProduct);
  
       
      const updatedCategory = categories.find(
        (category) => category.id === updatedProduct.category.id
      );
  
       
      const updatedProductWithCategory = {
        ...updatedProduct,
        category: updatedCategory,  
      };
  
       
      const updatedProducts = products.map((product) =>
        product.id === updatedProductWithCategory.id ? updatedProductWithCategory : product
      );
      setProducts(updatedProducts);
  
       
      setDisplayEditDialog(false);
    } catch (error) {
      console.error("Error al actualizar el producto:", error);
    }
  };

  const handleSubmit = async () => {
    try {
       
      const dataToSend = {
        name: formData.name,
        price: formData.price,
        status: formData.status,
        categoryId: formData.categoryId,  
      };
  
      let updatedProduct;
      if (selectedProduct) {
         
        updatedProduct = await updateProduct(selectedProduct.id, dataToSend);
      } else {
         
        updatedProduct = await createProduct(dataToSend);
      }
  
      console.log("Producto actualizado/creado:", updatedProduct);
  
       
      const updatedCategory = categories.find(
        (category) => category.id === updatedProduct.category.id
      );
  
       
      const updatedProductWithCategory = {
        ...updatedProduct,
        category: updatedCategory,  
      };
  
   
      if (selectedProduct) {
         
        const updatedProducts = products.map((product) =>
          product.id === updatedProductWithCategory.id ? updatedProductWithCategory : product
        );
        setProducts(updatedProducts);
      } else {
         
        setProducts([...products, updatedProductWithCategory]);
      }
  
      
      setDisplayEditDialog(false);
      setSelectedProduct(null);  
    } catch (error) {
      console.error("Error al actualizar/crear el producto:", error);
    }
  };

   
  const actionsTemplate = (rowData) => {
    return (
      <div>
        <Button
          icon="pi pi-pencil"
          className="p-button-rounded p-button-success p-mr-2"
          onClick={() => handleEdit(rowData)}
        />
        <Button
          icon="pi pi-trash"
          className="p-button-rounded p-button-danger"
          onClick={() => handleDelete(rowData)}
        />
      </div>
    );
  };

   
  const categoryTemplate = (rowData) => {
    return (
      <span className={`p-badge p-mr-2 ${rowData.category.status === "ENABLED" ? "p-badge-success" : "p-badge-danger"}`}>
        {rowData.category.name}
      </span>
    );
  };

  return (
    <div>
      <Navbar />
      <h1>Productos</h1>
      <Button
        label="Crear Producto"
        icon="pi pi-plus"
        className="p-button-success btn_ele_create"
        onClick={() => {
          setFormData({ name: "", price: 0, status: "ENABLED", categoryId: null });  
          setDisplayEditDialog(true);
        }}
      />


      <DataTable value={products} paginator rows={10} rowsPerPageOptions={[5, 10, 25]}>
        <Column field="name" header="Producto" sortable />
        <Column field="price" header="Precio" sortable />
        <Column field="status" header="Estado" sortable />
        <Column header="Categoría" body={categoryTemplate} sortable />
        <Column header="Acciones" body={actionsTemplate} />
      </DataTable>

       
      <Dialog
        header="Editar Producto"
        visible={displayEditDialog}
        onHide={() => setDisplayEditDialog(false)}
        style={{ width: "50vw" }}
      >
        <div className="p-fluid">
          <div className="p-field">
            <label htmlFor="name">Nombre</label>
            <InputText
              id="name"
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
            />
          </div>
          <div className="p-field">
            <label htmlFor="price">Precio</label>
            <InputText
              id="price"
              value={formData.price}
              onChange={(e) => setFormData({ ...formData, price: parseFloat(e.target.value) || 0 })}
            />
          </div>
          <div className="p-field">
            <label htmlFor="status">Estado</label>
            <Dropdown
              id="status"
              value={formData.status}
              options={[
                { label: "Habilitado", value: "ENABLED" },
                { label: "Deshabilitado", value: "DISABLED" },
              ]}
              onChange={(e) => setFormData({ ...formData, status: e.value })}
              placeholder="Selecciona un estado"
            />
          </div>
          <div className="p-field">
            <label htmlFor="category">Categoría</label>
            <Dropdown
              id="category"
              value={formData.categoryId}
              options={categories.map((category) => ({
                label: category.name,
                value: category.id,
              }))}
              onChange={(e) => setFormData({ ...formData, categoryId: e.value })}
              placeholder="Selecciona una categoría"
            />
          </div>
          <div className="p-field boton_guardar_ele">
            <Button label="Guardar" icon="pi pi-check" onClick={handleSubmit} />
          </div>
        </div>
      </Dialog>
    </div>
  );
};

export default Products;