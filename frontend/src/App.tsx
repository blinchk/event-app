import NavBar from "./components/NavBar/NavBar.tsx";

import './App.scss';
import {Route, Routes} from "react-router-dom";
import HomePage from "./routes/HomePage/HomePage.tsx";
import AddEventPage from "./routes/AddEventPage/AddEventPage.tsx";

function App() {
  return (
    <div>
      <NavBar />
      <Routes>
         <Route path='/' element={<HomePage/>} />
         <Route path='/event/add' element={<AddEventPage/>} />
      </Routes>
    </div>
  )
}

export default App
