import React from "react";
import { Routes, Route } from 'react-router-dom';



 function nothing() {
    return(
        <AppProvider>
        <Nav></Nav>
            <Routes>
            <Route path='/api/verifierUser' element={  <HomeForm/>}>


            </Route>
            </Routes>
        </AppProvider>
     

    )

}

export default nothing;



 



 



