import React, { useContext } from 'react';
import { UserOnline } from './UserOnline';



function HomePage() {
const { user } = useContext(UserOnline);

  if (!user) {
    return <p>Cargando usuario...</p>;
  }

  return (
    <>
      <div>Bienvenido {user.firstName}</div>
        <ul>
          <li>Usuarios</li>
          <li>Productos</li>
          <li>Extra</li>
          <li>Extra</li>
          <li>Extra</li>
        </ul>
   

      
    </>
  );
}

export default HomePage;
