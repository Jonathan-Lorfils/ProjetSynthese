import React, { useEffect, useState } from 'react'
import CustomerNavbar from './CustomerNavbar'
import CustomerFooter from './CustomerFooter'

const CustomerOrders = () => {

    const [customerOrders, setCustomerOrders] = useState([])
    const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))

    useEffect(() => {
        const getOrdersFromCustomer = async () => {
            const orderssFromCartFromServer = await getOrdersFromCustomerWS(userInfo.id)
            setCustomerOrders(orderssFromCartFromServer)
        }
        getOrdersFromCustomer()
    }, [])

    const getOrdersFromCustomerWS = async (customerId) => {
        const res = await fetch(`http://localhost:2022/orders/getAllOrdersByCustomer/${customerId}`)
        return await res.json()
    }

    console.log(customerOrders)

    return (
        <div>
            <div className="gradient-form gradient-custom-2">
                <CustomerNavbar />
                <div className="container justify-content-center align-items-center h-75">

                    {customerOrders.length === 0 ?  <h2 className=" mt-5 text-center text-light"> Vous n'avez aucune commande pour le moment</h2>  :

                        <table class="table table-light">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Etat</th>
                                    <th scope="col">Prix</th>
                                    <th scope="col">Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                {customerOrders
                                    .map((order) => (
                                        <tr key={order.id}>
                                            <td data-title="orderNumber">{order.id}</td>
                                            <td data-title="status">{order.status}</td>
                                            <td data-title="price">{order.price}</td>
                                            <td data-title="price">{order.date}</td>
                                        </tr>
                                    ))}
                            </tbody>
                        </table>
                    }
                </div>
            </div>
            <CustomerFooter />
        </div>
    )
}

export default CustomerOrders