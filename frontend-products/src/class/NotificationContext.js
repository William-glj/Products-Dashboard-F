import { createContext, useContext } from 'react';
//Creamos una nueva constante que almacene el contexto global de las notificaciones.
export const NotificationContext = createContext(); 
//Accedemos a dicho contexto con otra constante.
export const useNotification = () => useContext(NotificationContext);
