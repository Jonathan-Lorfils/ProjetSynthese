import React, { useState, useEffect } from 'react'

const AdminDisplayNftModal = ({ nftProp }) => {

    useEffect(() => {
        
    }, [nftProp])

    const viewCustomerProfile = () => {

    }

    const b64toBlob = (b64Data, contentType = '', sliceSize = 512) => {
        const byteCharacters = atob(b64Data);
        const byteArrays = [];

        for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
            const slice = byteCharacters.slice(offset, offset + sliceSize);

            const byteNumbers = new Array(slice.length);
            for (let i = 0; i < slice.length; i++) {
                byteNumbers[i] = slice.charCodeAt(i);
            }

            const byteArray = new Uint8Array(byteNumbers);
            byteArrays.push(byteArray);
        }

        const blob = new Blob(byteArrays, { type: contentType });
        return blob;
    }

    return (
        <div>
            <button type="button" className="btn btn-primary" data-toggle="modal" data-target={"#nft" + nftProp.id}>
                <span className="hideButtonText">Consulter <i className="fa-solid fa-book-open"></i> </span>
            </button>

            <div className="modal fade" id={"nft" + nftProp.id} tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog modal-dialog-centered modal-lg">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="exampleModalLabel">Validation</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="container">
                            <div className="row">
                                <div className="col-sm my-3">
                                    <img src={URL.createObjectURL(b64toBlob(nftProp.data, 'image/png'))} alt="" width="300" height="400" />
                                </div>
                                <div className="card-body p-md-5 mx-md-4">
                                    <div className="text-center">
                                        <h4 className="mt-1 mb-5 pb-1">Nom du NFT : {nftProp.name}</h4>
                                        <h5>Propriété de : {nftProp.owner.firstName} {nftProp.owner.lastName}</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AdminDisplayNftModal