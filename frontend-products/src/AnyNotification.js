// AnyComponent.js
import { useNotification } from './NotificationContext';

function AnyComponent() {
  const { notify } = useNotification();

  const handleClick = () => {
    notify({
      type: "error",
      message: "No se ha encontrado ningún usuario registrado",
      position: "top-center"
    });
  };

  return <button onClick={handleClick}>Mostrar notificación</button>;
}

 export default AnyComponent;