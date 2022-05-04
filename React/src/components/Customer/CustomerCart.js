import React, { useEffect, useState } from 'react'
import CustomerNavbar from './CustomerNavbar'
import { Notification } from "../Notifications.js"

const CustomerCart = () => {

    const [itemsFromCart, setItemsFromCart] = useState([])
    const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))

    useEffect(() => {
        const getItemsFromCartAtLaunch = async () => {
            const itemsFromCartFromServer = await getItemsFromCartWS(userInfo.cart.id)
            setItemsFromCart(itemsFromCartFromServer)
        }
        getItemsFromCartAtLaunch()
    }, [itemsFromCart, ])

    const getItemsFromCartWS = async (customerCartId) => {
        const res = await fetch(`http://localhost:2022/cart/getItems/${customerCartId}`)
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

    const removeItemFromCartWS = async (customerCartId, nftToAddId) => {
        const res = await fetch(`http://localhost:2022/cart/removeItemFromCart/${customerCartId}/${nftToAddId}`)
        return await res.json()
    }

    const removeItemFromCart = async (customerCartId, nftToAddId) => {
        removeItemFromCartWS(customerCartId, nftToAddId)
            .then((data) => data === true ? Notification.successNotification("Supression reussi") : Notification.failNotification("Erreur du retrait"))

        setItemsFromCart(await getItemsFromCartWS(customerCartId))
    }

    return (
        <div className="gradient-form gradient-custom-2">
            <CustomerNavbar />
            <div className="container justify-content-center align-items-center h-75">
                <div className=" jumbotron-fluid bg-light rounded shadow">
                    <h2>Votre Panier</h2>
                    {itemsFromCart
                        .map((nftProp) => (
                            <div className="container">
                                <div className="row">
                                    <div className="col-sm my-3">
                                        <img src={URL.createObjectURL(b64toBlob(nftProp.data, 'image/png'))} alt="" width="120" height="120" />
                                    </div>
                                    <button className=" btn btn-light" onClick={e => { removeItemFromCart(userInfo.cart.id, nftProp.id) }}><i class="fa-solid fa-trash"></i></button>
                                    <div className="card-body p-md-5 mx-md-4">
                                        <div className="text-center">
                                            <h4 className="mt-1 mb-5 pb-1">{nftProp.name}</h4>
                                            <h5>{nftProp.price} ETH</h5>
                                        </div>
                                    </div>
                                </div>
                                <hr />
                            </div>
                        ))}
                        <hr />
                        
                        <p>p</p>
                </div>
            </div>
        </div>
    )
}

export default CustomerCart