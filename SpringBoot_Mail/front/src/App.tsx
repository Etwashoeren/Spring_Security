import React, {ChangeEvent, useState} from 'react';

import './App.css';
import InputBox from "./components/InputBox";
import {Route, Routes} from "react-router-dom";
import SignUp from "./views/Authentication/SignUp";
import SignIn from "./views/Authentication/SignIn";

function App() {


  return (
    <Routes>
        <Route path={'/auth'}>
            <Route path={'sign-up'} element={<SignUp />} />
            <Route path={'sign-in'} element={<SignIn />} />
        </Route>
    </Routes>
  );
}

export default App;
