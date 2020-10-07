import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import FormRegistration from "./components/form-registration";
import { ToastContainer } from "react-toastify";
function App() {
  return (
    <div className="container">
      <ToastContainer />
      <FormRegistration />
    </div>
  );
}

export default App;
