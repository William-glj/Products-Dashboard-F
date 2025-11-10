import { createContext, useState, useEffect } from 'react';
//Creamos un contexto global, que nos permita compartir datos entre los componentes.
export const UserOnline = createContext();  
//Función a exportar.
export function UserProvider({ children }) {
  //Creamos la variable dinámica/reactiva de user.
  //user contendrá la información.
  //setUser nos permitirá actualizar o almacenar ese valor.
  //useState le da su estado inicial. Nulo.
  const [user, setUser] = useState(null);
  //El hook se ejecutará si aparece en pantalla.
  useEffect(() => {
    //Creamos la constante "storedUser" y obtenemos el elemento web 'user' si existe en la página para almacenarlo.
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      setUser(JSON.parse(storedUser));//Lo pasamos a JSON para trabajar con él.
    }
  }, []);
  //Cada que el usuario cambie de estado se ejecutara.
  useEffect(() => {
    if (user) {
      localStorage.setItem('user', JSON.stringify(user)); //Si se detecta un usuario lo guardará bajo la clave 'user'.
    } else {
      localStorage.removeItem('user');//SI no existiera usuario. Fuera nulo, se borraría el dato.
    }
  }, [user]);
  //Devolvemos el componente UserProvider que le dara acceso a los demás hijos.
  return (
    <UserOnline.Provider value={{ user, setUser }}>
      {children}
    </UserOnline.Provider>
  );
}

//export default UserProvider;

