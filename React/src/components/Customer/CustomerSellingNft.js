import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2'

const CustomerSellingNft = () => {

    const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
    const [nft, setNft] = useState(JSON.parse(sessionStorage.nftToSell))
    const { register, handleSubmit, formState: { errors } } = useForm()
    let history = useNavigate();

    function onSubmit(data) {

    }

    const putToSell = async (nft, state) => {
        const res = await fetch(`http://localhost:2022/nft/setNftToSell/${nft.id}/${state}`)
        const data = await res.json()
    }

    const puToSellSuccess = () => {
        Swal.fire({
            toast: true,
            position: 'top',
            icon: 'success',
            title: 'Status de vente du NFT \n mise a jour',
            showConfirmButton: false,
            timer: 2000
        })
    }

    const puToSellFail = () => {
        Swal.fire({
            title: "Une erreur est survenue \n lors de la mise a jour du status",
            icon: 'error',
            position: 'top',
            toast: true,
            timer: 2000,
            showConfirmButton: false,
            width: '400px',
        })
    }

    function sucess(data) {
        console.log(data)
        sessionStorage.setItem('user', JSON.stringify(data))
        alert("Ajout reussi")
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
                                    <h4 className="text-uppercase text-center mb-2">Mise en vente</h4>
                                    <form onSubmit={handleSubmit((data) => {
                                        onSubmit(data);
                                    })}>
                                        <br />
                                        <h5 className="text-center">Entrer le prix de vente souhaiter (ETH)</h5>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="walletAddress">Nom</label>
                                            <input disabled value={nft.name} type="text" id="walletAddress" className="form-control form-control-xs" />
                                        </div>
                                        <div className="form-outline mb-4">
                                            <label className="form-label" for="walletAddress">Prix (ETH)</label>
                                            <input type="number" step={0.001} min="0.001" id="walletAddress" className="form-control form-control-xs" {...register("fundToAdd", { required: true })} />
                                        </div>
                                        <div className="d-flex justify-content-center">
                                            <button type="submit" className="btn btn-danger btn-block btn-md">Vendre</button>
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

export default CustomerSellingNft