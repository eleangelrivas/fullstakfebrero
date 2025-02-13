import Clientestop3 from "../components/Clientestop3";
import Navbar from "../components/Navbar"; 
import Productostop3 from "../components/Productostop3";
import Ingresoxmes from "../components/Ingresoxmes";

const Resumen = () => {
  return (
    <div>
      <Navbar />
      <h1>Resumen</h1> 
      <div style={{ display: "flex", flexWrap: "wrap", gap: "20px" }}>
        <div style={{ flex: "1 1 45%" }}>
          <Productostop3 />
        </div>
        <div style={{ flex: "1 1 45%" }}>
          <Clientestop3 />
        </div>
        <div style={{ flex: "1 1 100%" }}>
          <Ingresoxmes />
        </div>
      </div>
    </div>
  );
};

export default Resumen;
