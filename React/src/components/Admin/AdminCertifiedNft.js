import React, { useState, useEffect } from 'react'
import AdminDisplayNftModal from './AdminDisplayNftModal';
import AdminNavbar from './AdminNavbar';

const AdminCertifiedNft = () => {

    const [nftsList, setNftsList] = useState([])

    useEffect(() => {
        const getNftsList = async () => {
            const customerListFromServer = await fetchCustomersList()
            setNftsList(customerListFromServer)
        }
        getNftsList()
    }, [])

    const certifiedNft = async (nft) => {
        const res = await fetch(`http://localhost:2022/nft/certifiedNft/${nft.id}`)
        const data = await res.json()

        setNftsList(
            nftsList.map(
                (nft1) => nft1.id === nft.id ? { ...nft1, certified: data.certified } : nft1
            )
        )
    }

    const deleteNft = (nft) => {

    }



    const fetchCustomersList = async () => {
        const res = await fetch(`http://localhost:2022/nft/getAllNftsWaitingForCertification`)
        return await res.json()
    }

    const viewCustomerProfile = () => {

    }

    return (
        <div>
            <div className="grad">
                <AdminNavbar />
                {nftsList.length != 0 ?
                    <div>
                        <h2 className="text-center">Clients en attente de certification</h2>
                        <div className="p-5">
                            <table className="table table-hover bg-light shadow-lg" id="no-more-tables">
                                <thead>
                                    <tr>
                                        <th scope="col">Proprietaire</th>
                                        <th scope="col">Nom du Nft</th>
                                        <th scope="col">Status</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {nftsList
                                        .map((nft) => (
                                            <tr key={nft.id}>
                                                <td data-title="Proprietaire">{nft.owner.firstName} {nft.owner.lastName}</td>
                                                <td data-title="Nom du Nft">{nft.name}</td>
                                                <td data-title="Validité">
                                                    <h5>
                                                        {nft.certified === false ?
                                                            <span className="badge badge-danger">
                                                                Invalide
                                                            </span>
                                                            :
                                                            <span className="badge badge-succes">
                                                                Valide
                                                            </span>
                                                        }
                                                    </h5>
                                                </td>
                                                <td className="responsiveWidth">
                                                    <AdminDisplayNftModal nft={nft} nftsList={nftsList} />
                                                </td>
                                                <td>
                                                    <button className="btn btn-success mx-2" onClick={e => { e.preventDefault(); certifiedNft(nft) }}>
                                                        <span className="hideButtonText">Valider </span>
                                                        <span className="hideButtonIcon"><i className="fas fa-check"></i></span>
                                                    </button>
                                                    <button className="btn btn-danger mx-2" onClick={e => { e.preventDefault(); deleteNft(nft) }}>
                                                        <span className="hideButtonText">Supprimer </span>
                                                        <span className="hideButtonIcon"><i className="fas fa-times"></i></span>
                                                    </button>
                                                </td>
                                            </tr>
                                        ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    : <h3 className="text-center mx-1">Aucun clients en attente de certification</h3>}
            </div>
        </div>
    )
}

export default AdminCertifiedNft