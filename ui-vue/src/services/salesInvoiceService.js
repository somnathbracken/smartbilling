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
  
  // export const searchProducts = async (keyword) => {
  //   const response = await fetch(`${ API_PRODUCT_URL }/search?keyword=${encodeURIComponent(keyword)}`)
  //   if (!response.ok) return []
  //   return await response.json()
  // }

  export const getProductByBarcode = async (barcode) => {
    //const response = await fetch(`${ API_PRODUCT_URL }/api/products/barcode/${barcode}`)
    const response = await fetch(`${ API_PRODUCT_URL }/search?keyword=${encodeURIComponent(barcode)}`)
    if (!response.ok) return null
    return await response.json()
  }
  export const searchProducts = async (keyword) => {
    const res = await fetch(`http://localhost:8082/api/products/search?keyword=${keyword}`)
    return await res.json()
  }
  
  export const searchProductByBarcode = async (barcode) => {
    const res = await fetch(`http://localhost:8082/api/products/search?keyword=${barcode}`)
    return await res.json()
  }

  // export const saveSalesInvoice = async (invoice) => {
  //   let invoices = JSON.parse(localStorage.getItem('invoices')) || []
  //   invoices.push(invoice)
  //   localStorage.setItem('invoices', JSON.stringify(invoices))
  // }
  export async function saveSalesInvoice(invoice) {
    const res = await fetch('http://localhost:8083/api/salesinvoices', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(invoice),
    })
    if (!res.ok) {
      throw new Error('Failed to save invoice')
    }
    return await res.json()
  }