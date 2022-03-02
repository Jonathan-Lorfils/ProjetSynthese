import React, { useEffect, useState } from 'react'
import CustomerNavbar from './CustomerNavbar'
import './ProfileStyle.css'
import logo from '.././../LazyLion.jpg'
import {Link} from 'react-router-dom'
import SellerCertificationModal from './SellerCertificationModal'

const Profile = () => {

  const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
  const [sellerCertificationState, setSellerCertificationState] = useState(userInfo.sellerCertification)

  const sellerCertificationValid = () => {
    return (
      <div>
        <p>Vendeur certifié <i class="fa-solid fa-circle-check"></i></p>
      </div>
    )
  }

  const sellerCertificationWaiting = () => {
    return (
      <div>
        <p>En attente <i class="fa-solid fa-circle-check"></i></p>
      </div>
    )
  }

  const sellerCertificationInvalid = () => {
    return (
      <div>
        <p>Pas de certification de vendeur <i class="fa-solid fa-circle-xmark"></i></p>
        <SellerCertificationModal/>
      </div>
    )
  }

  const checkSellerCertification = () => {
    if (sellerCertificationState == "Valide") {
      return sellerCertificationValid()
    } else if (sellerCertificationInvalid == "En attente") {
      return sellerCertificationWaiting()
    } else {
      return sellerCertificationInvalid()
    }
  }

  return (
    <div>
      <CustomerNavbar />
      <div class="page-content page-container" id="page-content">
        <div class="padding">
          <div class="row d-flex justify-content-center">
            <div class="col-xl-6 col-md-12">
              <div class="card user-card-full">
                <div class="row m-l-0 m-r-0">
                  <div class="col-sm bg-c-lite-green user-profile border-radius">
                    <div class="card-block text-center text-white">
                      <div class="m-b-25"> <img src={logo} className="img-radius" alt="User-Profile-Image" /> </div>
                      <div class="m-b-25">  </div>
                      <h3 class="f-w-600">{userInfo.firstName} {userInfo.lastName} </h3>
                      {checkSellerCertification()}
                      <h6>Solde disponible : {userInfo.solde} ETH</h6>
                      <Link to={"/withdrawfunds"}>
                        <button className="btn btn-light btn-sm mx-2 mt-2">Retirer des fonds</button>
                      </Link>
                      <Link to={"/addfunds"}>
                        <button className="btn btn-light btn-sm mt-2">Ajouter des fonds</button>
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Profile