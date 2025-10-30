import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserOnline } from './UserOnline';

function Form() {
  const [mail, setMail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();


 const { setUser } = useContext(UserOnline);

  

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
    
      const response = await fetch('api/user/verifyUser', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  },
  body: `mail=${encodeURIComponent(mail)}&password=${encodeURIComponent(password)}`
});

      const data = await response.json();
      console.log("Respuesta del backend:", data);
    
   

    if (response.ok & data.userOnline != null) {
       
             
      setUser(data.userOnline); // guarda el usuario
      //navigate(`/home/newpage`); 
      navigate(`/home/${data.userOnline.firstName}`); // redirige correctamente
      } else {
       
        alert(data.error || "Credenciales inválidas");
      }


    } catch (err) {
      alert("Error de conexión con el servidor.");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="mail">Mail</label>
      <input type="email" id="mail" value={mail} onChange={(e) => setMail(e.target.value)} required />

      <label htmlFor="password">Password</label>
      <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} required />

      <button type="submit">Subir</button>
    </form>
  );
}

export default Form;

