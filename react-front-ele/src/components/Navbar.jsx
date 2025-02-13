import React from 'react';
import { Link } from 'react-router-dom';
import { Card } from 'primereact/card';

const Navbar = () => {
  // Obtener rol desde localStorage
  const role = localStorage.getItem("role");

  // Definir las opciones del menú
  const allCards = [
    { title: 'Dashboard', path: '/dashboard', icon: 'pi pi-home' },
    { title: 'Productos', path: '/products', icon: 'pi pi-box' },
    { title: 'Ventas', path: '/ventas', icon: 'pi pi-chart-line' },
    { title: 'Clientes', path: '/clientes', icon: 'pi pi-users' },
    { title: 'Resumen', path: '/resumen', icon: 'pi pi-chart-pie' },
    { title: 'Salir', path: '/', icon: 'pi pi-sign-out' }
  ];

  //verifico que sea admin: en el role del jwt guardo: ADMINISTRATOR
  const filteredCards = role === "ADMINISTRATOR" 
    ? allCards 
    : allCards.filter(card => card.title === "Productos" || card.title === "Salir");

  return (
    <div className="elecard p-grid p-fluid p-mt-4">
      {filteredCards.map((card, index) => (
        <div key={index} className="p-col-12 p-md-6 p-lg-3">
          <Link to={card.path} style={{ textDecoration: 'none' }}>
            <Card className="p-shadow-8 p-m-2" title={card.title}>
              <div className="p-d-flex p-jc-center">
                <i className={card.icon} style={{ fontSize: '2em', color: '#4CAF50' }}></i>
              </div>
            </Card>
          </Link>
        </div>
      ))}
    </div>
  );
};

export default Navbar;
