import React, { useEffect, useState } from 'react'
import CustomerNavbar from './CustomerNavbar'

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
        <CustomerNavbar/>
        <h1 className="text-center text-light">Arrive bientot</h1>
        <div className="card-deck">
                {nftsToSellList
                  .map((nft) => (
                    <div key={nft.id} class="card shadow" style={{ width: '18rem' }}>
                      <img src={URL.createObjectURL(b64toBlob(nft.data, 'image/png'))} class="card-img-top" alt="..." />
                      <div class="card-body">
                        <h5 class="card-title">{nft.name}</h5>
                        <p class="card-text">Ce Nft vous appartient</p>
                      </div>
                      <div class="card-body">
                        <a href="#" class="card-link">Mettre en vente</a>
                        <a href="#" class="card-link">Retirer</a>
                      </div>
                    </div>
                  ))}
              </div>
    </div>
  )
}

export default CustomerShop