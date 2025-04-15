import axios from 'axios'

const API_URL = 'http://localhost:8080/api/customers'

export default {
  getAllCustomers() {
    return axios.get(API_URL+"/listAll").then(res => res.data)
  },
  saveCustomer(customer) {
    return axios.post(API_URL, customer)
  },
  updateCustomer(customer) {
    return axios.put(`${API_URL}/${customer.id}`, customer)
  },
  deleteCustomer(id) {
    return axios.delete(`${API_URL}/${id}`)
  },
  searchCustomers(name) {
    return axios.get(`${API_URL}/name/${name}`).then(res => res.data)
  }
}
