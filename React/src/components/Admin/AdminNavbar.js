import React from 'react'
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2'

const AdminNavbar = () => {

    let history = useNavigate();

    const disconnect = () => {
        sessionStorage.clear()
        history("/")
        disconnectSuccess()
    }

    const disconnectSuccess = () => {
        Swal.fire({
          toast: true,
          position: 'top',
          icon: 'success',
          title: 'Déconnexion réussi',
          showConfirmButton: false,
          timer: 2000
        })
      }

    return (
        <div>
            <nav className="navbar navbar-expand-md bg-light shadow mb-5">
                <a className="navbar-brand text-secondary"></a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="fas fa-bars btn btn-light"></span>
                </button>
                <div className="collapse navbar-collapse justify-content-between" id="navbarNavDropdown">
                    <ul className="navbar-nav">
                        <li className="nav-item mx-1">
                            <button className="nav-link btn btn-md btn-light mx-1"><i className="fas fa-home fa-lg"></i></button>
                        </li>
                        <li className="nav-item mx-1">
                            <button className="nav-link btn btn-md btn-light" onClick={e => { history("/adminSellerCertification") }} >Vendeurs</button>
                        </li>
                        <li className="nav-item mx-1">
                            <button className="nav-link btn btn-md btn-light" onClick={e => { history("/adminNftCertification") }} >NFT</button>
                        </li>
                    </ul>
                    <button className="btn btn-danger btn-md my-2 mx-2" onClick={e => { disconnect() }} >Déconnexion</button>
                </div>
            </nav>
        </div>
    )
}

export default AdminNavbar