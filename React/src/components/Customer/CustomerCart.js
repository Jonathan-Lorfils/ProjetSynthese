import React, { useEffect, useState } from 'react'
import CustomerNavbar from './CustomerNavbar'
import { Notification } from "../Notifications.js"

const CustomerCart = () => {

    const [itemsFromCart, setItemsFromCart] = useState([])
    const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
    const [cartTotalPrice, setCartTotalPrice] = useState()

    useEffect(() => {
        const getItemsFromCartAtLaunch = async () => {
            const itemsFromCartFromServer = await getItemsFromCartWS(userInfo.cart.id)
            setItemsFromCart(itemsFromCartFromServer)
        }
        getItemsFromCartAtLaunch()

        const updatedTotalPrice = async () => {
            const updatedTotalPriceFromServer = await getTotalPrice(userInfo.cart.id)
            setCartTotalPrice(updatedTotalPriceFromServer)
        }
        updatedTotalPrice()
    }, [])

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

    const getTotalPrice = async (customerCartId) => {
        const res = await fetch(`http://localhost:2022/cart/getTotalPrice/${customerCartId}`)
        return await res.json()
    }

    const validateCartWS = async (idNewOwner, customerCartId) => {
        const res = await fetch(`http://localhost:2022/cart/validateCart/${idNewOwner}/${customerCartId}`)
        return await res.json()
    }

    const createOrder = async (idNewOwner, customerCartId) => {
        validateCartWS(idNewOwner, customerCartId)
            .then((data) => data === true ? Notification.successNotification("Commande creer"): Notification.failNotification("Erreur dans la commande"))
    }

    return (
        <div className="gradient-form gradient-custom-2">
            <CustomerNavbar />
            <div className="container justify-content-center align-items-center h-75">

                {itemsFromCart.length == 0 ? <h2 className=" mt-5 text-center text-light">Panier vide</h2> :

                    <div className=" jumbotron-fluid bg-light rounded shadow">
                        <h1 className='text-center'>Votre Panier</h1>
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
                        <div className="container">
                            <div className="row">
                                <div className='col-sm'>
                                    <h3 className="text-left">Solde disponible: {userInfo.solde} ETH</h3>
                                </div>
                                <div className='col-sm'>
                                    <h3 className="text-right">Prix total: {cartTotalPrice} ETH</h3>
                                </div>
                            </div>
                            <div className="row">
                                <div className='col-sm'>
                                    <button className="btn btn-danger">Retourner a la boutique</button>
                                </div>
                                {userInfo.solde < cartTotalPrice ?
                                    <div className='col-sm'>
                                        <button className="btn btn-primary float-right">Deposer des fonds</button>
                                        <button className="btn btn-primary float-right" disabled>Solde insuffisant</button>
                                    </div> :
                                    <div className='col-sm'>
                                        <button className="btn btn-primary float-right" onClick={e => { createOrder(userInfo.id,userInfo.cart.id) }}>Acheter</button>
                                    </div>
                                }
                            </div>
                        </div>
                    </div>
                }
            </div>
        </div>
    )
}

export default CustomerCart