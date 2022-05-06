import React, { useEffect, useState } from 'react'
import CustomerNavbar from './CustomerNavbar'
import DisplayNftModals from '../DisplayNftModal'
import './CustomerShopCss.css'
import { Notification } from "../Notifications.js"
import CustomerFooter from './CustomerFooter'

const CustomerShop = () => {

  const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
  const [nftsToSellList, setNftsToSellList] = useState([])
  const [itemsFromCart, setItemsFromCart] = useState([])

  useEffect(() => {
    const getNftsToSellList = async () => {
      const nftsToSellListFromServer = await fetchCustomersNftsList(userInfo.id)
      setNftsToSellList(nftsToSellListFromServer)
    }
    getNftsToSellList()

    const getItemsFromCartAtLaunch = async () => {
      const itemsFromCartFromServer = await getItemsFromCartWS(userInfo.cart.id)
      setItemsFromCart(itemsFromCartFromServer)
    }
    getItemsFromCartAtLaunch()
  }, [])

  const fetchCustomersNftsList = async () => {
    const res = await fetch(`http://localhost:2022/nft/getAllCertifiedNftsToSell`)
    return await res.json()
  }

  const addItemToCartWS = async (customerCartId, nftToAddId) => {
    const res = await fetch(`http://localhost:2022/cart/addItemToCart/${customerCartId}/${nftToAddId}`)
    return await res.json()
  }

  const addItemToCart = async (customerCartId, nftToAddId) => {
    addItemToCartWS(customerCartId, nftToAddId)
      .then((data) => data === true ? Notification.successNotification("Ajout au panier reussi") : Notification.failNotification("Erreur lors de l'ajout"))

    const newCart = await getItemsFromCartWS(customerCartId)

    setItemsFromCart(newCart)
  }

  const getItemsFromCartWS = async (customerCartId) => {
    const res = await fetch(`http://localhost:2022/cart/getItems/${customerCartId}`)
    return await res.json()
  }

  const getItemsFromCart = async (customerCartId) => {
    return getItemsFromCartWS(customerCartId)
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

  return (
    <div className="gradient-form gradient-custom-2">
      <CustomerNavbar />
      <h1 className="text-center text-light">Boutique</h1>
      {nftsToSellList.length == 0 ? <h2 className=" mt-5 text-center text-light">Aucun NFT n'est en vente pour le moment, revenez plus tard</h2> :
        <div className="container mt-lg-5 h-75">
          <div className="row row-cols-1 row-cols-md-3">
            {nftsToSellList
              .map((nft) => (
                <div className="col mb-4">
                  <div key={nft.id} className="card card shadow mr-4" style={{ width: '18rem', height: '30rem' }}>
                    <img src={URL.createObjectURL(b64toBlob(nft.data, 'image/png'))} className="card-img-top" alt="..." />
                    <div className="card-body">
                      <h6>{nft.name}</h6>
                      <h5 className="card-title">Prix: {nft.price} ETH</h5>
                    </div>
                    <div className="card-body">
                      {nft.owner.id !== userInfo.id ?
                        itemsFromCart.some(nft1 => nft1['id'] === nft.id) ?
                          <button className="btn btn-primary btn-sm mr-3" disabled >Nft deja dans le panier</button> :
                          <button className="btn btn-primary btn-sm mr-3" onClick={e => { addItemToCart(userInfo.cart.id, nft.id) }}>Ajouter au panier</button>
                        : <h6>Ce NFT vous appartient</h6>}
                      <button className="btn btn-danger btn-sm">En savoir plus</button>
                    </div>
                  </div>
                </div>
              ))}
          </div>
        </div>
      }
      <CustomerFooter/>
    </div>
  )
}

export default CustomerShop