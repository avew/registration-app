import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import RegistrationPage from "./components/registration-page";
import LoginPage from "./components/login-page";
import { ToastContainer } from "react-toastify";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import history from "./utils/history";
function App() {
  return (
    <Router history={history}>
      <div className="container">
        <ToastContainer />
        <Switch>
          <Route path="/login">
            <LoginPage />
          </Route>
          <Route path="/registration">
            <RegistrationPage />
          </Route>
          <Route path="/">
            <RegistrationPage />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
