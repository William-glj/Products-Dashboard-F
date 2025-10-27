import React from "react";
import { Routes, Route } from 'react-router-dom';
import HomeForm from './HomeForm';
import HomePage from './HomePage';
import { UserProvider } from './UserOnline';

function POF() {
  return (
    <UserProvider>
      <Routes>
        <Route path="/" element={<HomeForm />} />
        
        <Route path="/home/newpage" element={<HomePage />} />

      </Routes>
</UserProvider>
  );
}

export default POF;





 



