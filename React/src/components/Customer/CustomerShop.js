import React, { useEffect, useState } from 'react'
import CustomerNavbar from './CustomerNavbar'
import DisplayNftModals from '../DisplayNftModal'
import './CustomerShopCss.css'

const CustomerShop = () => {

  const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
  const [nftsToSellList, setNftsToSellList] = useState([])

  useEffect(() => {
    const getNftsToSellList = async () => {
      const nftsToSellListFromServer = await fetchCustomersNftsList(userInfo.id)
      setNftsToSellList(nftsToSellListFromServer)
    }
    getNftsToSellList()
  }, [])

  const fetchCustomersNftsList = async () => {
    const res = await fetch(`http://localhost:2022/nft/getAllCertifiedNftsToSell`)
    return await res.json()
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
    <div className="gradient-custom-2"> 
      <CustomerNavbar />
      <h1 className="text-center text-light">Boutique</h1>
      {nftsToSellList.length == 0 ? <h2 className=" mt-5 text-center text-light">Aucun NFT en vente pour le moment revenez plus tard</h2> :
        <div className="container mt-lg-5">
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
                      <button className="btn btn-primary btn-sm mr-3">Ajouter au panier</button>
                      <button className="btn btn-danger btn-sm">En savoir plus</button>
                    </div>
                  </div>
                </div>
              ))}
          </div>
        </div>
      }
    </div>
  )
}

export default CustomerShop