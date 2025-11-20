import React, { useContext, useEffect } from 'react';
import { UserListOnline } from "../class/UserListOnline";
import { UserOnline } from '../class/UserOnline';
import { Link } from 'react-router-dom';
import '../css/StandardFormat.css';

function UserView() {
  const { user } = useContext(UserOnline);
  const { list, setListUser } = useContext(UserListOnline);

  useEffect(() => {
    const fetchList = async () => {
      try {
        const rolIn = user?.rol;

        if (!rolIn || !["Administrador", "Usuario", "Invitado"].includes(rolIn)) {
          console.error("Rol inválido o no definido");
          return;
        }

        const response = await fetch(`/api/user/allUsers?rol=${rolIn}`, {
          method: 'GET',
        });

        const data = await response.json();
        const userList = data.ListUsers; //coincide el backend

        if (Array.isArray(userList)) {
          setListUser(userList);
          console.log(userList);
        } else {
          console.error("La respuesta no contiene una lista válida");
        }
      } catch (err) {
        alert("Error de conexión con el servidor. Ha fallado la obtención de usuarios.");
      }
    };

    fetchList();
  }, [user, setListUser]);

  return (
    <div className="container">
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
         
        </header>

        <section className="applications">
          <h2>Usuarios registrados</h2>
          <div className="card-list">
            {list?.map(u => (
              <div className="card" key={u.idUser}>
                <h3>{u.first_name}</h3>
                <p>{u.company_mail}</p>
              </div>
            ))}
          </div>
        </section>
      </main>
    </div>
  );
}

export default UserView;
