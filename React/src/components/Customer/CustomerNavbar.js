import React from 'react'

const CustomerNavbar = () => {
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
                            <button className="nav-link btn btn-md btn-light" >Portefeuille</button>
                        </li>
                        <li className="nav-item mx-1">
                            <button className="nav-link btn btn-md btn-light" >Profil</button>
                        </li>
                    </ul>
                    <button className="btn btn-danger btn-md my-2 mx-2" >Déconnexion</button>
                </div>
            </nav>
        </div>
  )
}

export default CustomerNavbar