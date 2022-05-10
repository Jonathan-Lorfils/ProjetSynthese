import React from 'react'

const AdminProfileModal = ({ customerProp }) => {

    return (
        <div>
            <button type="button" className="btn btn-primary" data-toggle="modal" data-target={"#customer" + customerProp.id}>
                <span className="hideButtonText">Consulter <i class="fa-solid fa-user"></i> </span>
            </button>
            <div className="modal fade" id={"customer" + customerProp.id} tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog modal-dialog-centered modal-lg">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="exampleModalLabel">Profil </h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="container">
                                <div className="card-body p-md-5 mx-md-4">
                                    <div className="text-center">
                                        <table className='table'>
                                            <tr>
                                                <th>Nom</th>
                                                <td>{customerProp.firstName} {customerProp.lastName}</td>
                                            </tr>
                                            <tr>
                                                <th>Numero de telephone</th>
                                                <td>{customerProp.phoneNumber}</td>
                                            </tr>
                                            <tr>
                                                <th>Courriel</th>
                                                <td>{customerProp.email}</td>
                                            </tr>
                                            <tr>
                                                <th>Addresse du portefeuille</th>
                                                <td>{customerProp.walletAddress}</td>
                                            </tr>
                                            <tr>
                                                <th>Status de la certification</th>
                                                <td>{customerProp.sellerCertification}</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AdminProfileModal