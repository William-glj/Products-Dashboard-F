import React from "react";
import { Routes, Route } from 'react-router-dom';
import HomeForm from './viewerpage/HomeForm';
import HomePage from './viewerpage/HomePage';
import ProductsView from './viewerpage/ProductsView';
import UserView from './viewerpage/UserView';
import { UserProvider } from './class/UserOnline';
import { UserListProvider } from './class/UserListOnline';
import { ProductProvider } from './class/ProductOnline';
import { NotificationProvider } from './class/NotificationProvider';



function POF() {
  return (
    <UserProvider>
      <NotificationProvider>
           <ProductProvider>
            <UserListProvider>
                    <Routes>
                        <Route path="/" element={<HomeForm />} />
                        <Route path="/home/:firstName" element={<HomePage />} />
                        <Route path="/home/:firstName/products" element={<ProductsView />} />
                        <Route path="/home/:firstName/allUsers" element={<UserView />} />
                    </Routes>
            </UserListProvider>
          </ProductProvider>
      </NotificationProvider>
    </UserProvider> 
  );
}

export default POF;




 



