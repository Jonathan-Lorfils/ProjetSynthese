import React, { useState, useEffect } from 'react'
import AdminNavbar from './AdminNavbar';

const AdminSellerCertification = () => {

  const [customersList, setCustomersList] = useState([])

  useEffect(() => {
    const getCustomersList = async () => {
      const customerListFromServer = await fetchCustomersList()
      setCustomersList()
    }
    getCustomersList()
  }, [])

  const fetchCustomersList = async () => {
    const res = await fetch(`http://localhost:8888/admin/getAllCustomersWaitingForCertification/${customer.id}`)
    return await res.json()
  }


  return (
    <div>
         <div>
            <div className="grad">
                <AdminNavbar />
                <div className="p-5">
                    <table className="table table-hover bg-light shadow-lg" id="no-more-tables">
                        <thead>
                            <tr>
                                <th scope="col">Nom </th>
                                <th scope="col">Validité </th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            {customersList
                                .filter(filterDocuments)
                                .map((document) => (
                                    <tr key={document.idDocument}>
                                        <td data-title="Nom">{document.documentName}</td>
                                        <td data-title="Validité">
                                            <h5>
                                                <span className={`badge ${!document.isValid && !document.isRefused ? 'badge-warning' :
                                                    !document.isValid && document.isRefused ? 'badge-danger' : 'badge-success'}`}>
                                                    {!document.isValid && !document.isRefused ? 'En attente' :
                                                        !document.isValid && document.isRefused ? 'Refusé' : 'Valide'}
                                                </span>
                                            </h5>
                                        </td>
                                        <td className="responsiveWidth">
                                            <button className="btn btn-primary mx-2" onClick={e => { e.preventDefault(); viewDocumentCv(document) }}>
                                                <span className="hideButtonText">Consulter</span>
                                                <span className="hideButtonIcon"><i className="fas fa-book-open"></i></span>
                                            </button>
                                            {!document.isValid && !document.isRefused ? displayButtons(document) : ""}
                                        </td>
                                    </tr>
                                ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
  )
}

export default AdminSellerCertification