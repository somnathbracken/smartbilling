export default {
  async getCategories() {
    return [
      { id: 1, name: 'Electronics' },
      { id: 2, name: 'Groceries' }
    ]
  },
  async addCategory(category) {
    const newId = Math.floor(Math.random() * 1000) + 3
    return { id: newId, name: category.name }
  },
  async saveProduct(product) {
    console.log("Saving product:", product)
    return { success: true }
  }
}