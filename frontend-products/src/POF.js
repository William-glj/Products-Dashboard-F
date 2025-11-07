import React from "react";
import { Routes, Route } from 'react-router-dom';
import HomeForm from './HomeForm';
import HomePage from './HomePage';
import ProductsView from './ProductsView';
import { UserProvider } from './UserOnline';
import { ProductProvider } from './ProductOnline';
import { NotificationProvider } from './NotificationProvider';


//Dentro de  <UserProvider> se encuentrán los componentes hijos con acceso.
function POF() {
  return (
    <UserProvider>
      <NotificationProvider>
           <ProductProvider>

                    <Routes>
                        <Route path="/" element={<HomeForm />} />
                        <Route path="/home/:firstName" element={<HomePage />} />
                        <Route path="/home/:firstName/products" element={<ProductsView />} />
                    </Routes>
          </ProductProvider>
      </NotificationProvider>
    </UserProvider> 
  );
}

export default POF;




 



