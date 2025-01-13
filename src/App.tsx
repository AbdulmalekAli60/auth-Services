import "./App.css";
import HomePage from "./components/HomePage";
import Singin from "./components/Singin";
import Singup from "./components/Singup";
import { Routes, Route } from "react-router-dom";
import { AlertProvider } from "./contexts/AlertContext";
function App() {
  return (
    <AlertProvider>
      <div>
        {/* routes */}
        <Routes>
          {/* http://localhost:5173/ */}
          <Route path="/" element={<HomePage />} />
          {/* http://localhost:5173/signup */}
          <Route path="/signup" element={<Singup />} />
          {/* http://localhost:5173/signin */}
          <Route path="/signin" element={<Singin />} />
        </Routes>
        {/* routes */}
      </div>
    </AlertProvider>
  );
}

export default App;
