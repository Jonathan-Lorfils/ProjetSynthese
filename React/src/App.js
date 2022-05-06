import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Login from './components/Login/Login'
import Register from './components/Register/Register';
import AddFunds from './components/Customer/AddFunds';
import WithdrawFunds from './components/Customer/WithdrawFunds';
import AdminSellerCertification from './components/Admin/AdminSellerCertification';
import UploadNFT from './components/Customer/UploadNFT';
import CustomerWallet from './components/Customer/CustomerWallet';
import AdminCertifiedNft from './components/Admin/AdminCertifiedNft';
import CustomerShop from './components/Customer/CustomerShop';
import CustomerSellingNft from './components/Customer/CustomerSellingNft';
import CustomerCart from './components/Customer/CustomerCart';
import CustomerOrders from './components/Customer/CustomerOrders';


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/wallet" element={<CustomerWallet />} />
        <Route path="/addfunds" element={<AddFunds />} />
        <Route path="/withdrawfunds" element={<WithdrawFunds />} />
        <Route path="/uploadNFT" element={<UploadNFT />} />
        <Route path="/adminSellerCertification" element={<AdminSellerCertification/>} />
        <Route path="/adminNftCertification" element={<AdminCertifiedNft/>} />
        <Route path="/CustomerShop" element={<CustomerShop/>} />
        <Route path="/CustomerSellingNft" element={<CustomerSellingNft/>}></Route>
        <Route path="/Cart" element={<CustomerCart/>}></Route>
        <Route path="/Orders" element={<CustomerOrders/>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
