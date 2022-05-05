import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import { Notification } from '../Notifications'

const AddFunds = () => {
    const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
    const { register, handleSubmit, formState: { errors } } = useForm()
    let history = useNavigate();

    const addfunds = async (fundToAdd) => {
        const res = await fetch(`http://localhost:2022/customer/addfunds/${fundToAdd}/${userInfo.phoneNumber}`)
        return await res.json()
    }

    function onSubmit(data) {
        addfunds(data.fundToAdd)
            .then((data) => data !== data.email ? sucess(data) : Notification.failNotification("echec de l'ajout"))
    }

    function sucess(data) {
        console.log(data)
        sessionStorage.setItem('user', JSON.stringify(data))
        Notification.successNotification("Ajout reussi")
        history("/wallet");
    }


    return (
        <section className="vh-100 bg-image ">
            <div className="mask d-flex align-items-center h-100 gradient-custom-3">
                <div className="container h-100">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div className="card">

                                <div className="card-body p-5">
                                    <h4 className="text-uppercase text-center mb-2">Ajouter des fonds</h4>
                                    <form onSubmit={handleSubmit((data) => {
                                        onSubmit(data);
                                    })}>
                                        <br />
                                        <h5 className="text-center">Envoyer uniquement de l'Ethereum(ETH) a cette adresse</h5>
                                        <div className="form-outline mb-4">
                                            <input disabled value="Lorem5ipsum2dolor8sit9amet1consectetur3adipisicing" type="text" id="walletAddress" className="form-control form-control-xs" />
                                        </div>
                                        <p className="text-center"><u onClick={() => navigator.clipboard.writeText('Lorem5ipsum2dolor8sit9amet1consectetur3adipisicing')}>Copier</u></p>
                                        <p className="text-center"><u>Montant actuel : {userInfo.solde} (ETH)</u></p>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="walletAddress">Montant (ETH)</label>
                                            <input type="number" step={0.001} min="0.001" id="walletAddress" className="form-control form-control-xs" {...register("fundToAdd", { required: true })} />
                                        </div>
                                        <div className="d-flex justify-content-center">
                                            <button type="submit" className="btn btn-danger btn-block btn-md">Ajouter</button>
                                        </div>
                                        <div className="mt-4 d-flex justify-content-center">
                                        <button className="btn btn-primary" onClick={e => { e.preventDefault(); history("/wallet"); }}>
                                                <i className="fas fa-angle-double-left"></i> Retour
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}

export default AddFunds