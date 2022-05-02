import React, { useEffect, useState } from 'react'
import CustomerNavbar from './CustomerNavbar'
import './CustomerWalletStyle.css'
import logo from '.././../LazyLion.jpg'
import { Link } from 'react-router-dom'
import SellerCertificationModal from './SellerCertificationModal'
import { useNavigate } from 'react-router-dom';
import { Notification } from '../Notifications';

const CustomerWallet = () => {

  const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
  const [sellerCertificationState, setSellerCertificationState] = useState(userInfo.sellerCertification)
  const [customerNftsList, setCustomerNftsList] = useState([])
  let history = useNavigate();

  useEffect(() => {
    const getCustomersNftsList = async () => {
      const customerNftsListFromServer = await fetchCustomersNftsList(userInfo.id)
      setCustomerNftsList(customerNftsListFromServer)
    }
    getCustomersNftsList()
  }, [])

  const fetchCustomersNftsList = async (idOwner) => {
    const res = await fetch(`http://localhost:2022/nft/getAllNftByOwner/${idOwner}`)
    return await res.json()
  }

  const sellerCertificationValid = () => {
    return (
      <div>
        <p>Vendeur certifié <i className="fa-solid fa-circle-check"></i></p>
      </div>
    )
  }

  const sellerCertificationWaiting = () => {
    return (
      <div>
        <p>En attente <i className="fa-solid fa-circle-check"></i></p>
      </div>
    )
  }

  const sellerCertificationInvalid = () => {
    return (
      <div>
        <p>Pas de certification de vendeur <i className="fa-solid fa-circle-xmark"></i></p>
        <SellerCertificationModal />
      </div>
    )
  }

  const checkSellerCertification = () => {
    if (sellerCertificationState == "Valide") {
      return sellerCertificationValid()
    } else if (sellerCertificationState == "En attente") {
      return sellerCertificationWaiting()
    } else {
      return sellerCertificationInvalid()
    }
  }

  const b64toBlob = (b64Data, contentType = '', sliceSize = 512) => {
    const byteCharacters = atob(b64Data);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
      const slice = byteCharacters.slice(offset, offset + sliceSize);

      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }

      const byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }

    const blob = new Blob(byteArrays, { type: contentType });
    return blob;
  }

  const goToCustomerSellingNft = (nft) => {
    sessionStorage.setItem('nftToSell', JSON.stringify(nft))
    history("/CustomerSellingNft")
  }

  const withdrawFromSale = async (nft) => {
    const res = await fetch(`http://localhost:2022/nft/setNftToSell/${nft.id}/${false}/${0}`)
    const data = await res.json()
    if (data.id == nft.id) {
      setCustomerNftsList(
        customerNftsList.map(
          (nft1) => nft1.id === nft.id ? nft1 = data : nft1
        )
      )
      Notification.successNotification("Votre Nft a été retiré de la vente")
      return
    }
    Notification.failNotification("Une erreur est survenue \n lors de la mise a jour du status")
  }

  const showPutNftToSellButton = (nft) => {
    return (
      <div>
        <button className="btn btn-primary card-link" onClick={e => { goToCustomerSellingNft(nft) }}>Mettre en vente</button>
      <button className="btn btn-danger card-link" disabled>Retirer</button>
      </div>
      
    )
  } 

  const showRemoveNftFromSellButton = (nft) => {
    return (
      <div>
        <button className="btn btn-success card-link" disabled>Déjà en vente</button>
        <button className="btn btn-danger card-link" onClick={e => { withdrawFromSale(nft) }}>Retirer</button>
      </div>
    )
  }

  const hideSellButton = () => {
    return (
      <h6>Pas de certification vendeur</h6>
    )
  }

  return (
    <div>
      <CustomerNavbar />
      <div className="page-content page-container" id="page-content">
        <div className="padding">
          <div className="row d-flex justify-content-center">
            <div className="col-xl-7 col-md-5">
              <div className="card user-card-full">
                <div className="row m-l-0 m-r-0">
                  <div className="col-sm bg-c-lite-green user-profile border-radius">
                    <div className="card-block text-center text-white">
                      <div className="m-b-25"> <img src={logo} className="img-radius" alt="User-Profile-Image" /> </div>
                      <div className="m-b-25">  </div>
                      <h3 className="f-w-600">{userInfo.firstName} {userInfo.lastName} </h3>
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
              <div className="container mt-lg-5">
                <div className="row row-cols-1 row-cols-md-3">
                  {customerNftsList
                    .map((nft) => (
                      <div className="col mb-4">
                        <div key={nft.id} className="card shadow" style={{ width: '18rem' }}>
                          <img src={URL.createObjectURL(b64toBlob(nft.data, 'image/png'))} className="card-img-top" alt="..." />
                          <div className="card-body">
                            <h5 className="card-title">{nft.name}</h5>
                            <p className="card-text">Ce Nft vous appartient</p>
                          </div>
                          <div className="card-body">
                            {userInfo.sellerCertification == "Valide"  ? 
                            nft.toSell == true ?
                              showRemoveNftFromSellButton(nft) :
                              showPutNftToSellButton(nft)
                            :
                             hideSellButton()
                            }
                          </div>
                        </div>
                      </div>
                    ))}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
export default CustomerWallet