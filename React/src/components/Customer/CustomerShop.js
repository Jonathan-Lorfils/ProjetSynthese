import React from 'react'
import CustomerNavbar from './CustomerNavbar'

const CustomerShop = () => {

  const [userInfo, setUserInfo] = useState(JSON.parse(sessionStorage.user))
  const [sellerCertificationState, setSellerCertificationState] = useState(userInfo.sellerCertification)
  const [customerNftsList, setCustomerNftsList] = useState([])

  useEffect(() => {
    const getCustomersNftsList = async () => {
      const customerNftsListFromServer = await fetchCustomersNftsList(userInfo.id)
      setCustomerNftsList(customerNftsListFromServer)
    }
    getCustomersNftsList()
  }, [])

  const fetchCustomersNftsList = async (idOwner) => {
    const res = await fetch(`http://localhost:2022/nft/getAllNftByOwner/${idOwner}`)
    return await res.json()
  }

  
  return (
    <div className="gradient-custom-2">
        <CustomerNavbar/>
        <h1 className="text-center text-light">Arrive bientot</h1>
    </div>
  )
}

export default CustomerShop