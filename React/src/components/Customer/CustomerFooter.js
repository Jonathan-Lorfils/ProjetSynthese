import React from 'react'
import { useNavigate } from 'react-router-dom';

const CustomerFooter = () => {

    let history = useNavigate();

    return (
        <div>
            <footer class="page-footer deep-purple center-on-small-only pt-0">

                <div class="container">

                    <div class="row pt-5 mb-3 text-center d-flex justify-content-center">

                        <div class="col-md-2 mb-3">
                            <h6 class="title font-bold"><a href="/wallet">Portefeuille</a></h6>
                        </div>

                        <div class="col-md-2 mb-3">
                            <h6 class="title font-bold"><a href="/uploadNFT">Televerser NFT</a></h6>
                        </div>

                        <div class="col-md-2 mb-3">
                            <h6 class="title font-bold"><a href="/CustomerShop">Boutique</a></h6>
                        </div>

                        <div class="col-md-2 mb-3">
                            <h6 class="title font-bold"><a href="/Cart">Panier</a></h6>
                        </div>

                        <div class="col-md-2 mb-3">
                            <h6 class="title font-bold"><a href="/Orders">Commandes</a></h6>
                        </div>
                    </div>

                    <hr class="rgba-white-light" style={{margin: '0 15%'}} />
                    <div class="row d-flex text-center justify-content-center mb-md-0 mb-4">
                        <div class="col-md-8 col-12 mt-5">
                            <p style={{lineheight: '1.7rem'}}>SafeNft permet aux acheteurs de faire l’acquisition de NFT de manière simple et sécuriser. 
                            Les transactions se font uniquement sous forme de ethereum (cryptomonnaies) 
                            afin de garantir l’anonymat de nos acheteurs. </p>
                        </div>
                    </div>

                    <hr class="clearfix d-md-none rgba-white-light" style={{margin: '10% 15% 5%'}} />
                    
                    <div class="row pb-3 pt-3">
                        <div class="col-md-12">
                            <div class="footer-socials mb-5 d-flex justify-content-center flex-center">

                                <a class="icons-sm fb-ic"><i class="fa-brands fa-facebook fa-lg white-text mr-md-4"> </i></a>

                                <a class="icons-sm tw-ic"><i class="fa-brands fa-twitter fa-lg white-text mx-md-4"> </i></a>

                                <a class="icons-sm gplus-ic"><i class="fa-brands fa-google-plus fa-lg white-text mx-md-4"> </i></a>

                                <a class="icons-sm li-ic"><i class="fa-brands fa-linkedin fa-lg white-text mx-md-4"> </i></a>

                                <a class="icons-sm ins-ic"><i class="fa-brands fa-instagram fa-lg white-text mx-md-4"> </i></a>

                                <a class="icons-sm pin-ic"><i class="fa-brands fa-pinterest fa-lg white-text ml-md-4"> </i></a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="footer-copyright">
                    <div class="container-fluid d-flex justify-content-center">
                       <p> © 2016 Copyright: <a href="/"> SafeNFT.com </a></p> 
                    </div>
                </div>
            </footer>
        </div>
    )
}

export default CustomerFooter
