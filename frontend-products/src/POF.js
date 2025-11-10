import React from "react";
import { Routes, Route } from 'react-router-dom';
import HomeForm from './viewerpage/HomeForm';
import HomePage from './viewerpage/HomePage';
import ProductsView from './viewerpage/ProductsView';
import { UserProvider } from './class/UserOnline';
import { ProductProvider } from './class/ProductOnline';
import { NotificationProvider } from './class/NotificationProvider';



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




 



