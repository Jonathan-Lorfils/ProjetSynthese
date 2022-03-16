import React, { useState, useEffect } from 'react'

const AdminDisplayNftModal = ({ nft }) => {

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

    const data = b64toBlob(nft.data, 'image/png')

    return (
        <div>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                <span className="hideButtonText">Consulter <i class="fa-solid fa-book-open"></i> </span>
            </button>

            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Validation</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col-sm">
                                    <img src={URL.createObjectURL(data)} alt="" width="300" height="400" />
                                </div>
                                <div className="card-body p-md-5 mx-md-4">
                                    <div className="text-center">
                                        <h4 className="mt-1 mb-5 pb-1">Nom du NFT : {nft.name}</h4>
                                        <h5>Propriété de : {nft.owner.firstName} {nft.owner.lastName}</h5>
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