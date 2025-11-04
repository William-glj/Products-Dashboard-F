import { NotificationContext } from './NotificationContext';
import { toast, ToastContainer, Bounce } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
//Creamos el componente principal que envolvera la notificación.
export const NotificationProvider = ({ children }) => {
  //Arrow Function que envuelve la constate que recoge e inicia la función de la notificación.
  const notify = ({ type = "info", message, position = "top-center" }) => {
    toast[type](message, {
      position,
      autoClose: 3500,
      hideProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      draggable: true,
      theme: "light",
      transition: Bounce,
    });
  };
  //Este componente envuelve la aplicación con NotificationContext.Provider para compartir la función 
  //notify con todos sus hijos, permitiendo mostrar notificaciones desde cualquier parte.
  return (
    <NotificationContext.Provider value={{ notify }}>
      {children}
      <ToastContainer />
    </NotificationContext.Provider>
  );
};
