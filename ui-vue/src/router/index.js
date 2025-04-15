import { createRouter, createWebHistory } from 'vue-router'
import CustomerGrid from '../views/CustomerGrid.vue'

const routes = [
  { path: '/', redirect: '/customers' },
  { path: '/customers', component: CustomerGrid },
]

export default createRouter({
  history: createWebHistory(),
  routes,
})
