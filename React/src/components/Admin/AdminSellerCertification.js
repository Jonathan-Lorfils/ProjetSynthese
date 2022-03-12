import React, { useState, useEffect } from 'react'
import AdminNavbar from './AdminNavbar';

const AdminSellerCertification = () => {

  const [customersList, setCustomersList] = useState([])

  useEffect(() => {
    const getCustomersList = async () => {
      const customerListFromServer = await fetchCustomersList()
      setCustomersList(customerListFromServer)
    }
    getCustomersList()
  }, [])

  const fetchCustomersList = async () => {
    const res = await fetch(`http://localhost:2022/customer/getAllCustomersWaitingForCertification`)
    return await res.json()
  }

  const setSellerCertification = async (customer, state) => {
    const res = await fetch(`http://localhost:2022/customer/requestSellerCertification/${customer.phoneNumber}/${state}`)
    const data = await res.json()

    setCustomersList(
      customersList.map(
        (customer1) => customer1.id === customer.id ? { ...customer1, sellerCertification: data.sellerCertification } : customer1
      )
    )
  }

  const viewCustomerProfile = () => {
    
  }



  return (
    <div>
      <div className="grad">
        <AdminNavbar />

        {customersList.length != 0 ?
          <div>
            <h2 className="text-center">Clients en attente de certification</h2>
            <div className="p-5">
              <table className="table table-hover bg-light shadow-lg" id="no-more-tables">
                <thead>
                  <tr>
                    <th scope="col">Prenom</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Status</th>
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
                            <span className={`badge ${customer.sellerCertification === "En attente" ? 'badge-warning' : customer.sellerCertification === "Valide" ? 'badge-success' : 'badge-danger'}`}>
                              {customer.sellerCertification}
                            </span>
                          </h5>
                        </td>
                        <td className="responsiveWidth">
                          <button className="btn btn-primary mx-2" onClick={e => { e.preventDefault(); viewCustomerProfile(customer) }}>
                            <span className="hideButtonText">Consulter </span>
                            <span className="hideButtonIcon"><i class="fa-solid fa-user"></i></span>
                          </button>
                          <button className="btn btn-success mx-2" onClick={e => { e.preventDefault(); setSellerCertification(customer, "Valide") }}>
                            <span className="hideButtonText">Accepter </span>
                            <span className="hideButtonIcon"><i className="fas fa-check"></i></span>
                          </button>
                          <button className="btn btn-danger mx-2" onClick={e => { e.preventDefault(); setSellerCertification(customer, "Invalide") }}>
                            <span className="hideButtonText">Refuser </span>
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

export default AdminSellerCertification