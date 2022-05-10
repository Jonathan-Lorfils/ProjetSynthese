import React, { useState } from "react";
import './RegisterStyle.css'
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import Notification from "../Notifications";


const Register = () => {

    const { register, handleSubmit, formState: { errors } } = useForm()
    let history = useNavigate();

    const registerUser = async (userJSON) => {
        const result = await fetch('http://localhost:2022/customer/register',
            {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(userJSON)
            })
        return await result.json()
    }

    function onSubmit(data) {
        if (data.username.includes("admin")) {
            alert("Votre nom d'utilisateur ne peut pas contenir admin")
            return
        } 
        registerUser(data)
            .then((data1) => data1.email !== undefined ? registration() : Notification.failNotification("Échec de l'inscription"))
            .catch(() => Notification.failNotification("Échec de l'inscription"))
    }

    function registration() {
        Notification.successNotification('Inscription reussi')
        history("/");
    }

    return (
        <section className="vh-100 bg-image ">
            <div className="mask d-flex align-items-center h-100 gradient-custom-3">
                <div className="container h-100">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div className="card">
                                <div className="card-body p-5">
                                    <h4 className="text-uppercase text-center mb-2">Créer un compte</h4>
                                    <form onSubmit={handleSubmit(onSubmit)}>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="firstName">Prénom</label>
                                            <input type="text" id="firstName" className="form-control form-control-xs"  {...register("firstName", { required: true, max: 46 })} />
                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="lastName">Nom de famille</label>
                                            <input type="text" id="lastName" className="form-control form-control-xs" {...register("lastName", { required: true, max: 46 })} />
                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="phoneNumber">Téléphone</label>
                                            <input type="text" id="phoneNumber" className="form-control form-control-xs"  {...register("phoneNumber", { required: true, minLength: 10, maxLength: 10 })} />
                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="email">Adresse courriel</label>
                                            <input type="email" id="email" className="form-control form-control-xs" {...register("email", { required: true, max: 62 })} />
                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="password">Nom d'utilisateur</label>
                                            <input type="text" id="username" className="form-control form-control-xs" {...register("username", { required: true, max: 65, regex: "[a-zA-Z]+" })} />
                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="password">Mot de passe</label>
                                            <input type="password" id="password" className="form-control form-control-xs" {...register("password", { required: true })} />
                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="walletAddress">Adresse du porte-monnaie (ETH)</label>
                                            <input type="text" id="walletAddress" className="form-control form-control-xs" {...register("walletAddress", { required: true })} />
                                        </div>
                                        <div className="d-flex justify-content-center">
                                            <button type="submit" className="btn btn-danger btn-block btn-md">S'inscrire</button>
                                        </div>
                                        <p className="text-center text-muted mt-4 mb-2">Vous avez déjà un compte ? <a href="/" className="fw-bold text-body"><u>Connectez-vous</u></a></p>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default Register;