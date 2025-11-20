import { createContext, useState, useEffect } from 'react';

// Creamos el contexto con valores por defecto
export const UserListOnline = createContext({
  list: [],
  setListUser: () => {}
});

// Proveedor del contexto
export function UserListProvider({ children }) {
  const [list, setListUser] = useState([]);

  // Cargar usuarios desde localStorage al iniciar
  useEffect(() => {
    try {
      const stored = localStorage.getItem('list');
      if (stored) {
        const parsed = JSON.parse(stored);
        if (Array.isArray(parsed)) {
          setListUser(parsed);
        }
      }
    } catch (error) {
      console.error("Error al cargar los usuarios del localStorage:", error);
    }
  }, []);

  // Guardar en localStorage cuando cambien
  useEffect(() => {
    try {
      if (Array.isArray(list) && list.length > 0) {
        localStorage.setItem('list', JSON.stringify(list));
      } else {
        localStorage.removeItem('list');
      }
    } catch (error) {
      console.error("Error al guardar los usuarios en localStorage:", error);
    }
  }, [list]);

  return (
    <UserListOnline.Provider value={{ list, setListUser }}>
      {children}
    </UserListOnline.Provider>
  );
}
