import React from "react";
import { useForm } from 'react-hook-form';
import './style.css'
import { useNavigate } from 'react-router-dom';

const Login = () => {

  const { register, handleSubmit, formState: { errors } } = useForm()
  let history = useNavigate();

  const userLogin = async (email, password) => { 
    const res = await fetch(`http://localhost:2022/user/${email}/${password}`)
    return await res.json()
  }

  function onSubmit(data) {
    userLogin(data.email,data.password)
      .then((data) => data.email != null ? signIn() : console.log(data))
      //.then((data) => data.email != null ? signIn() : alert("Echec de la connexion"))
  }

  function signIn (){
    history("/dashboard")
  }

  return (
    <section className="h-100 gradient-form">
      <div className="container py-5 h-100">
        <div className="row justify-content-center align-items-center h-100">
          <div className="col-xl-10">
            <div className="card rounded-3 text-black">
              <div className="row g-0">
                <div className="col-lg-6 d-flex align-items-center gradient-custom-2">
                  <div className="text-white px-3 py-4 p-md-5 mx-md-4">
                    <h4 className="mb-4">Bienvenue sur SafeNft</h4>
                    <p className="small mb-0">Le meilleur site d'achat et vente de NFT au Canada !</p>
                  </div>
                </div>
                <div className="col-lg-6">
                  <div className="card-body p-md-5 mx-md-4">
                    <div className="text-center">
                      <h4 className="mt-1 mb-5 pb-1">SafeNFT</h4>
                    </div>
                    <form onSubmit={handleSubmit((data) => {
                      onSubmit(data);
                    })}>
                      <p>Se connecter</p>
                      <div className="form-group mb-4">
                        <input className="form-control" type="email" placeholder="Adresse courriel" {...register("email", { required: true, pattern: /^\S+@\S+$/i })} />
                      </div>
                      <div className="form-group mb-4">
                        <input className="form-control" type="password" placeholder="Mot de passe" {...register("password", { required: true, maxLength: 128 })} />
                      </div>
                      <div className="text-center pt-1 mb-5 pb-1">
                        <button className="btn btn-primary btn-block mb-3" type="submit">Connection</button>
                      </div>
                      <div className="d-flex align-items-center justify-content-center pb-4">
                        <p className="mb-0 me-2 px-md-2">Pas de compte ?</p>
                        <a href="/register" className="fw-bold text-body"><button type="button" className="btn btn-outline-primary">S'inscrire</button></a>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Login;
