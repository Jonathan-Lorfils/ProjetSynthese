import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Login from './components/Login/Login'
import Register from './components/Register/Register';
import Profile from './components/Customer/Profile';
import AddFunds from './components/Customer/AddFunds';
import WithdrawFunds from './components/Customer/WithdrawFunds';
import AdminSellerCertification from './components/Admin/AdminSellerCertification';


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/addfunds" element={<AddFunds />} />
        <Route path="/withdrawfunds" element={<WithdrawFunds />} />
        <Route path="adminSellerCertification" element={<AdminSellerCertification/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
