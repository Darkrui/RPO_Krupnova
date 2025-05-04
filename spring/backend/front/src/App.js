import "./App.css";
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
//import { createBrowserHistory } from "history";
import NavigationBar from "./components/NavigationBar";
import Home from "./components/Home";
<<<<<<< HEAD
import Login from "./components/Login";
=======
>>>>>>> a7346feb6c5203e7cc8e62c640906fbf9e1fcf49

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <NavigationBar />
        <div className="container-fluid">
          <Routes>
            <Route path="home" element={<Home />} />
<<<<<<< HEAD
            <Route path="login" element={<Login />} />
=======
>>>>>>> a7346feb6c5203e7cc8e62c640906fbf9e1fcf49
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
