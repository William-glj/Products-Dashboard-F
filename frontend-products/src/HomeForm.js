import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserOnline } from './UserOnline';
import { useNotification } from './NotificationContext';

function Form() {
  const [mail, setMail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const { setUser } = useContext(UserOnline);
  const { notify } = useNotification(); 

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
    
      const response = await fetch('api/user/verifyUser', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: `mail=${encodeURIComponent(mail)}&password=${encodeURIComponent(password)}`
      });

      const data = await response.json();
      console.log("Respuesta del backend:", data);
    
    if (response.ok & data.userOnline != null) {
       
      setUser(data.userOnline); // guarda el usuario
      navigate(`/home/${data.userOnline.firstName}`); // redirige correctamente
      } else {
       
        notify({
        type: "info",
        message: data.error || "No se ha encontrado el usuario. ¿Seguro que la cuenta existe?",
        position: "top-center"
      });



        //alert(data.error || "Credenciales inválidas");
      }


    } catch (err) {
      alert("Error de conexión con el servidor. Ha fallado la zona de inicio de sesión");
    }
  };

  return (
    //JSX tiene que tener un elemento raiz
    <div className='firstBox'>
    <header>

    <h1>Bienvenido de vuelta</h1>
    <p>Inicia sesión para ingresar a la aplicación</p>

    </header>
    
    <form onSubmit={handleSubmit}>
      <label htmlFor="mail">Mail</label>
      <input type="email" id="mail" value={mail} onChange={(e) => setMail(e.target.value)} required />

      <label htmlFor="password">Password</label>
      <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} required />

      <button type="submit">Subir</button>
    </form>

    <footer>


    </footer>

    </div>
  );
}

export default Form;

