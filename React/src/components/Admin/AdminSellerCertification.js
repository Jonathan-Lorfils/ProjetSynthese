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
    const res = await fetch(`http://localhost:8888/customer/getAllCustomersWaitingForCertification`)
    return await res.json()
  }

  const viewCustomerProfile = (customer) => {
    
  }

  const acceptRequest = (customer) => {
    
}

const declineRequest = (customer) => {
  
}


  return (
    <div>
      <div className="grad">
        <AdminNavbar />
        <h2 className="text-center text-light">Offres de stage</h2>
        <div className="p-5">
          <table className="table table-hover bg-light shadow-lg" id="no-more-tables">
            <thead>
              <tr>
                <th scope="col">Entreprise</th>
                <th scope="col">Poste</th>
                <th scope="col">Salaire</th>
                <th scope="col">Date d'affichage</th>
                <th scope="col">Validité</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              {customersList
                .map((customer) => (
                  <tr key={customer.id}>
                    <td data-title="Entreprise">{customer.firstName}</td>
                    <td data-title="Poste">{customer.lastName}</td>
                    <td data-title="Validité">
                      <h5>
                        <span className={`badge ${customer.sellerCertification ? 'badge-success' : customer.sellerCertification === null ? 'badge-warning' : 'badge-danger'}`}>
                          {customer.sellerCertification === null ? "En attente" : customer.sellerCertification}
                        </span>
                      </h5>
                    </td>
                    <td className="responsiveWidth">
                      <button className="btn btn-primary mx-2" onClick={e => { e.preventDefault(); viewCustomerProfile(customer) }}>
                        <span className="hideButtonText">Consulter</span>
                        <span className="hideButtonIcon"><i className="fas fa-book-open"></i></span>
                      </button>
                      <button className="btn btn-success mx-2" onClick={e => { e.preventDefault(); acceptRequest(customer) }}>
                        <span className="hideButtonText">Publier</span>
                        <span className="hideButtonIcon"><i className="fas fa-check"></i></span>
                      </button>
                      <button className="btn btn-danger mx-2" onClick={e => { e.preventDefault(); declineRequest(customer) }}>
                        <span className="hideButtonText">Retirer</span>
                        <span className="hideButtonIcon"><i className="fas fa-times"></i></span>
                      </button>
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default AdminSellerCertification