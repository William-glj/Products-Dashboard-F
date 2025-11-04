import React from "react";
import { Routes, Route } from 'react-router-dom';
import HomeForm from './HomeForm';
import HomePage from './HomePage';
import { UserProvider } from './UserOnline';
import { NotificationProvider } from './NotificationProvider';


//Dentro de  <UserProvider> se encuentrán los componentes hijos con acceso.
function POF() {
  return (
    <UserProvider>
      <NotificationProvider>

        <Routes>
            <Route path="/" element={<HomeForm />} />
            <Route path="/home/:firstName" element={<HomePage />} />
      
          </Routes>

      </NotificationProvider>
    </UserProvider> 
  );
}

export default POF;




 



