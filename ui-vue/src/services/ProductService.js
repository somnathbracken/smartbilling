export async function fetchAllProducts() {
  return [
    { id: 1, name: 'Product A', sku: 'A100', unit_price: 100, quantity: 10, status: 'active' },
    { id: 2, name: 'Product B', sku: 'B200', unit_price: 200, quantity: 5, status: 'inactive' }
  ]
}

export async function saveProductData(product) {
  console.log('Saving', product)
}

export async function fetchCategories() {
  return [
    { id: 1, name: 'Electronics' },
    { id: 2, name: 'Apparel' }
  ]
}
