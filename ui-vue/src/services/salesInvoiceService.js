// src/services/salesInvoiceService.js

import axios from 'axios'

const API_PRODUCT_URL = 'http://localhost:8082/api/products'
const API_CUSTOMER_URL = 'http://localhost:8080/api/customers'

  export const fetchCustomers = async (keyword) => {
      return axios.get(`${API_CUSTOMER_URL}/search?keyword=${encodeURIComponent(keyword.value)}`)
  }
  
  export const addCustomerToDb = async (customer) => {
    return axios.post(API_CUSTOMER_URL, customer)
  }
  
  export const saveSalesInvoice = async (invoice) => {
    let invoices = JSON.parse(localStorage.getItem('invoices')) || []
    invoices.push(invoice)
    localStorage.setItem('invoices', JSON.stringify(invoices))
  }
  