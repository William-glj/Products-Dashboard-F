
import React from 'react';


//Archivo actual -> HomeForm.js
//Ventana principal de la aplicación
//Para verificar usuarios
function Form() {
  return (
   
    /*Los atributo name="mail" y name="password"
     deben coincidir
      con los nombres esperados en @RequestParam.
    */
  <form method="get" action="/api/verifierUser">
  <label htmlFor="mail">Mail</label>
  <input type="email" id="mail" name="mail" required />

  <label htmlFor="password">Password</label>
  <input type="password" id="password" name="password" required />

  <button type="submit">Subir</button>
  </form>





  )
}

export default Form;
