// src/services/CustomerService.js

import axios from 'axios'

const API_URL = 'http://localhost:8080/api/customers'

export default {
  getAll() {
    return axios.get(API_URL+"/listAll")
  },
  get(id) {
    return axios.get(`${API_URL}/${id}`)
  },
  create(data) {
    return axios.post(API_URL, data)
  },
  update(id, customer) {
    return axios.put(`${API_URL}/${id}`, customer);
  },
  remove(id) {
    return axios.delete(`${API_URL}/${id}`)
  },
  search(keyword) {
    return axios.get(`${API_URL}/search?keyword=${encodeURIComponent(keyword)}`)
  }
}
