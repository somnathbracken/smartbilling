import { createRouter, createWebHistory } from 'vue-router'
import CustomerGrid from './views/CustomerGrid.vue'
import VendorGrid from './views/VendorGrid.vue'
import ProductView from './views/ProductView.vue'

const routes = [
  { path: '/', redirect: '/customers' },
  { path: '/customers', component: CustomerGrid },
  { path: '/vendors', component: VendorGrid },
  { path: '/products', component: ProductView }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
