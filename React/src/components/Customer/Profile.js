import React, { useEffect, useState } from 'react'
import CustomerNavbar from './CustomerNavbar'
import './ProfileStyle.css'
import logo from '.././../LazyLion.jpg'

const Profile = () => {

  const [userInfo, setUserInfo] = useState()

  useEffect(() => {
    setUserInfo(JSON.parse(sessionStorage.user))
  }, [])


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
                      <h3 class="f-w-600">{userInfo.firstName} {userInfo.lastName}  </h3>
                      <p>Vendeur certifié <i class="fa-solid fa-circle-check"></i></p>
                      <br />
                      <h6>Solde disponible : {userInfo.solde} ETH</h6>
                      <p>Ajouter des fonds</p>
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