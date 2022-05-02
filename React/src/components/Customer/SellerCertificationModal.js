import React, { useEffect, useState } from 'react'
import './ModalCss.css'
import { Notification } from '../Notifications';


const SellerCertificationModal = () => {

    const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
    const waitingState = "En attente";

    const requestSellerCertification = async () => {
        const res = await fetch(`http://localhost:2022/customer/setCustomerSellerCertification/${userInfo.phoneNumber}/${waitingState}`)
        return await res.json()
    }

    function sendRequest(data) {
        requestSellerCertification()
            .then((data) => data.sellerCertification === "En attente" ? sucess(data) : Notification.failNotification("Échec de la requete"))
    }

    function sucess(data) {
        setUserInfo(data)
        sessionStorage.setItem('user', JSON.stringify(data))
        Notification.successNotification("Requête envoye")
    }

    return (
        <div>
            <button type="button" class="btn btn-light btn-sm mb-3" data-toggle="modal" data-target="#exampleModal">
                Faire une demande
            </button>

            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <h6>Envoyer une demande pour une certification de vendeur ?</h6>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                            <button type="button"  disabled={userInfo.sellerCertification == "En attente"} onClick={() => { sendRequest() }} class="btn btn-primary">Confirmer</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default SellerCertificationModal