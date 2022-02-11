import React, { useState } from "react";
import './style.css'
import { useForm } from 'react-hook-form';

const Register = () => {

    const { register, handleSubmit, formState: { errors } } = useForm()
    const [user, setUser] = useState({firstName:"", lastName:"", phoneNumber:"", email:"", walletAddress:"", password: ""})

    const addMonitor = async (monitor) => {
        const result = await fetch('http://localhost:8888/monitors/register',
          {
            method: 'POST',
            headers: {
              'Content-type': 'application/json'
            },
            body: JSON.stringify(monitor)
          })
        return await result.json()
    }


    function onSubmit(data) {
        console.log(data)
    }

    return (
        <section className="vh-100 bg-image">
            <div className="mask d-flex align-items-center h-100 gradient-custom-3">
                <div className="container h-100">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div className="card">
                                <div className="card-body p-5">
                                    <h2 className="text-uppercase text-center mb-5">Creer un compte</h2>

                                    <form onSubmit={handleSubmit((data) => {
                                        onSubmit(data);
                                    })}>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="firstName">Prenom</label>
                                            <input type="text" id="firstName" className="form-control form-control-lg"  {...register("firstName", { required: true, max: 46 })} />

                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="lastName">Nom de famille</label>
                                            <input type="text" id="lastName" className="form-control form-control-lg" {...register("lastName", { required: true, max: 46 })} />
                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="phoneNumber">Telephone (514-000-0000)</label>
                                            <input type="tel" id="phoneNumber" className="form-control form-control-lg"  {...register("phoneNumber", { required: true, max: 10 })} />
                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="email">Adresse courriel</label>
                                            <input type="email" id="email" className="form-control form-control-lg" {...register("email", { required: true, max: 62 })} />

                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="password">Mot de passe</label>
                                            <input type="password" id="password" className="form-control form-control-lg" {...register("password", { required: true })} />

                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="walletAddress">Adresse du porte-monnaie (ETH)</label>
                                            <input type="text" id="walletAddress" className="form-control form-control-lg" {...register("walletAddress", { required: true })} />
                                        </div>
                                        <div className="d-flex justify-content-center">
                                            <button type="submit" className="btn btn-success btn-block btn-lg gradient-custom-4 text-body">S'inscrire</button>
                                        </div>
                                        <p className="text-center text-muted mt-5 mb-0">Vous avez deja un compte ? <a href="/" className="fw-bold text-body"><u>Connectez-vous</u></a></p>
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
