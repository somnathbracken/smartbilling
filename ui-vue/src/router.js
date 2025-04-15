import { createRouter, createWebHistory } from 'vue-router'
import CustomerGrid from './views/CustomerGrid.vue'

const routes = [
  { path: '/', redirect: '/customers' },
  { path: '/customers', component: CustomerGrid }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
