import React, { useContext, useEffect } from 'react';
import { UserOnline } from './UserOnline';
import { useNotification } from './NotificationContext';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';

function HomePage() {
  const { notify } = useNotification(); 
  const { user, setUser } = useContext(UserOnline);
  const navigate = useNavigate(); 
  
  //Para comprobar cambios constantes es necesario envolver el condicional en useEffect.
  //o cosas similares como un handleSubmit, handleLogin, ..etc.
 useEffect(() => {
  if (!user) {
    notify({
      type: "error",
      message: "Sin usuario no puedes acceder al entorno de la red",
      position: "top-center"
    });
    navigate("/");
  }
}, [user, notify, navigate]); 



    //<h1>Bienvenido {user?.firstName || "invitado"}</h1> es necesario
    //tener un valor de respaldo/predefinido para el segmento. Porque si el usuario se vuelve
    //nulo jamas reacciona el useEffect antes del error web.
  return (
    <>
      <div> 
        <h1>Bienvenido {user?.firstName || "invitado"}</h1>
        <button onClick={() => { setUser(null); }}>
          Cerrar sesión
        </button>
      </div>
      <header>
        <ul>
          <li>Usuarios</li>

          <li>
          <Link to={`/home/${user?.firstName}/products` || "Nada"}>Productos</Link>
          </li>
          <li>Extra</li>
          <li>Extra</li>
          <li>Extra</li>
        </ul>

      </header>
      <footer>


      </footer>
    
    </>
  );
}

export default HomePage;
