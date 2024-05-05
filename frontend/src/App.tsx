import NavBar from "./components/NavBar/NavBar.tsx";

import './App.scss';
import {Outlet} from "react-router-dom";

function App() {
  return (
    <div id="app">
      <NavBar />
      <Outlet />
    </div>
  )
}

export default App
