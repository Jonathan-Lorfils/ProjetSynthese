import React from 'react'
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';

const AddFunds = () => {
    const { register, handleSubmit, formState: { errors } } = useForm()
    let history = useNavigate();

    const addfunds = async (userJSON) => {
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
        addfunds(data)
            .then((data) => data.email !== undefined ? sucess() : alert("echec de l'ajout"))
    }

    function sucess() {
        // save dans le navigateur
        alert("Inscription reussi")
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
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="walletAddress">Montant</label>
                                            <input type="number" id="walletAddress" className="form-control form-control-xs" />
                                        </div>
                                        <div className="d-flex justify-content-center">
                                            <button type="submit" className="btn btn-danger btn-block btn-md">Ajouter</button>
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