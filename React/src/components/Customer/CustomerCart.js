import React, { useEffect, useState } from 'react'
import CustomerNavbar from './CustomerNavbar'
import { Notification } from "../Notifications.js"
import { useNavigate } from 'react-router-dom';
import CustomerFooter from './CustomerFooter'
import { DisplayImage } from '../DisplayImage';

const CustomerCart = () => {

    const [itemsFromCart, setItemsFromCart] = useState([])
    const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
    const [cartTotalPrice, setCartTotalPrice] = useState()
    let history = useNavigate();

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
    }, [itemsFromCart,])

    const getItemsFromCartWS = async (customerCartId) => {
        const res = await fetch(`http://localhost:2022/cart/getItems/${customerCartId}`)
        return await res.json()
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
        const res = await fetch(`http://localhost:2022/cart/validateCart/${idNewOwner}/${customerCartId}`,
        {
          method:'PUT'
        })
        return await res.json()
    }

    const createOrder = async (idNewOwner, customerCartId) => {
        validateCartWS(idNewOwner, customerCartId)
            .then((data) => data === true ? orderSuccess() : Notification.failNotification("Erreur dans la commande"))
    }

    const orderSuccess = () => {
        Notification.successNotification("Commande creer")
        history("/wallet")
    }

    return (
        <div className="gradient-form gradient-custom-2">
            <CustomerNavbar />
            <div className="container justify-content-center align-items-center h-100">
                {itemsFromCart.length == 0 ? <h1 className=" mt-5 text-center text-light">Panier vide</h1> :
                    <div className=" jumbotron-fluid bg-light rounded shadow">
                        <h1 className='text-center'>Votre Panier</h1>
                        {itemsFromCart
                            .map((nftProp) => (
                                <div className="container">
                                    <div className="row">
                                        <div className="col-sm my-3">
                                            <img src={URL.createObjectURL(DisplayImage.b64toBlob(nftProp.data, 'image/png'))} alt="" width="120" height="120" />
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
                                    <div className='col-sm mb-3'>
                                        <button className="btn btn-primary float-right ml-2" onClick={e => { history("/addfunds") }}>Déposer des fonds</button>
                                        <button className="btn btn-primary float-right" disabled>Solde insuffisant</button>
                                    </div> :
                                    <div className='col-sm mb-3'>
                                        <button className="btn btn-primary float-right" onClick={e => { createOrder(userInfo.id, userInfo.cart.id) }}>Acheter</button>
                                    </div>
                                }
                            </div>
                        </div>
                    </div>
                }
            </div>
            <CustomerFooter />
        </div>
    )
}

export default CustomerCart