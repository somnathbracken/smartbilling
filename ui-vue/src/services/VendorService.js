// src/services/CustomerService.js

import axios from 'axios'

const API_URL = 'http://localhost:8081/api/vendors'

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
  update(id, vendor) {
    return axios.put(`${API_URL}/${id}`, vendor);
  },
  remove(id) {
    return axios.delete(`${API_URL}/${id}`)
  },
  search(keyword) {
    return axios.get(`${API_URL}/search?keyword=${encodeURIComponent(keyword)}`)
  }
}
