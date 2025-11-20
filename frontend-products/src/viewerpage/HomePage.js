import React, { useContext, useEffect } from 'react';
import { UserOnline } from '../class/UserOnline';
import { useNotification } from '../class/NotificationContext';
import { useNavigate, Link  } from 'react-router-dom';

import '../css/StandardFormat.css';

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
    
      <div class="container">
            <aside className="sidebar">
              <div className="logo">join</div>
              <nav>
                <ul>
                  <li><Link to={`/home/${user?.firstName}/allUsers`}>Usuarios</Link></li>
                  <li><Link to={`/home/${user?.firstName}/products`}>Productos</Link></li>
                  <li><Link to={`/home/${user?.firstName}`}>Perfil</Link></li>
                  <li><span>Registros</span></li>
                </ul>
              </nav>
            </aside>
            <main className="content">
               <header className="header">
          <p className="date">{new Date().toLocaleDateString()}</p>
          <h1>Bienvenido {user?.firstName || "invitado"}</h1>
          <h1>Email {user?.companyMail || "invitado"}</h1>
           <h1>Acceso de tipo {user?.rol || "invitado"}</h1>
          <button className="logout" onClick={() => setUser(null)}>Cerrar sesión</button>
        </header>

              <section className="applications">
                
                <div className="card-list">
                  
                </div>
              </section>
            </main>
       
      </div>
  );
}

export default HomePage;
