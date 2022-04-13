import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';

const WithdrawFunds = () => {

  const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
  const { register, handleSubmit, formState: { errors } } = useForm()
  let history = useNavigate();

  const addfunds = async (fundToRemove) => {
    const res = await fetch(`http://localhost:2022/customer/withdrawfunds/${fundToRemove}/${userInfo.phoneNumber}`)
    return await res.json()
  }

  function onSubmit(data) {
    addfunds(data.fundToRemove)
      .then((data) => data !== data.email ? sucess(data) : alert("echec de l'ajout"))
  }

  function sucess(data) {
    sessionStorage.setItem('user', JSON.stringify(data))
    alert("retrait reussi")
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
                  <h4 className="text-uppercase text-center mb-2">Retirer des fonds</h4>
                  <form onSubmit={handleSubmit((data) => {
                    onSubmit(data);
                  })}>
                    <br />
                    <h5 className="text-center">Assurer vous que votre adresse peut recevoir de l'Ethereum(ETH)</h5>
                    <div className="form-outline mb-4">
                      <label className="form-label" for="walletAddress">Adresse du portefeuille (ETH)</label>
                      <input disabled value={userInfo.walletAddress} type="text" id="walletAddress" className="form-control form-control-xs" />
                    </div>
                    <p className="text-center"><u>Montant disponible au retrait : {userInfo.solde} (ETH)</u></p>
                    <div className="form-outline mb-4">
                      <label className="form-label" for="walletAddress">Montant (ETH)</label>
                      <input type="number" step={0.001} min="0.003" id="walletAddress" className="form-control form-control-xs" {...register("fundToRemove", { required: true, max: userInfo.solde })} />
                    </div>
                    <div className="d-flex justify-content-center">
                      <button type="submit" className="btn btn-danger btn-block">Retirer</button>
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

export default WithdrawFunds